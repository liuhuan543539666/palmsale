# 项目分析
***
## 框架[SpringBoot](http://projects.spring.io/spring-boot/),[Spring-Data-Jpa](http://projects.spring.io/spring-data-jpa/)
## 组件[jwt](https://jwt.io/),[spring security](http://projects.spring.io/spring-security/)
## 模式：前后分离，接口rest风格，前端使用vue开发，前后通讯http&https
 - rest : httpStatus:
    - 1xx:多指请求正在处理中，临时的响应
    - 2xx:多指服务器成功地接受了客户端请求，eg：200
    - 3xx:客户端浏览器必须采取更多操作来实现请求，eg：307重定向
    - 4xx:客户端错误，404：未找到url资源，401：请求授权失败，403：没有权限，400：错误请求，一般指参数错误，405：methode错误
    - 5xx:服务端错误，
  - rest : requestMethod:
    - get：一般指客户端向服务器请求资源
    - post：一般指客户端向服务器提交资源
    - delete：删除
    - put：更新
    - options：ajax发起的http&https请求在跨域的情况下，会先发送一个options请求，检测跨域请求是否可用，当得到200回复后才会真正调用后续请求
    - ...
***
  - http request header
    - Host  指定请求的服务器的域名和端口号
    - User-Agent  User-Agent的内容包含发出请求的用户信息
    - Accept  指定客户端能够接收的内容类型  Accept:application/json, text/javascript, */*;
    - Accept-Charset 浏览器可以接受的字符编码集 Accept-Charset: iso-8859-5
    - Content-Type  请求的与实体对应的MIME信息  Content-Type: application/x-www-form-urlencoded
  - http response header
    - Allow 对某网络资源的有效的请求行为，不允许则返回405  Allow: GET, HEAD
    - Cache-Control 告诉所有的缓存机制是否可以缓存及哪种类型  Cache-Control: no-cache
    - Content-Length 响应体的长度 Content-Length: 348
    - Content-Type  返回内容的MIME类型  Content-Type: text/html; charset=utf-8
***
## 项目流程
   - client send a httprequest -->  springmvc --> fitter --> inteceptor --> 
   controller --> responty --> mysql --> javaBean --> responty --> controller 
   --> inteceptor --> client
   - 项目使用fitter比较多，在mvcconfig中注册fitter并制定作用范围
   - Cross类--设置了，跨域访问
     - origins：允许访问服务器的ip地址*表示全部允许
     - headers：允许请求头中任意参数，当然这只是springmvc允许，这些都可以在外围web容器中拦截比如：nginx
     - Credentials：凭证此处不知，无意义
     - methods：请求方法全通过
     - age：设置缓存时效1小时3600秒
     ```
     @Configuration
     public class Cross extends WebMvcConfigurerAdapter {
     
         @Override
         public void addCorsMappings(CorsRegistry registry) {
             registry.addMapping("/**")
                     .allowedOrigins("*")
                     .allowedHeaders("*")
                     .allowCredentials(true)
                     .allowedMethods("*")
                     .maxAge(3600);
         }
     
     }
     ```
   - WebSecurityConfig --访问控制mvc
     - 指定了可以不登录访问的url资源
     - 同时制定访问受限的url资源
     - 加入登录校验拦截器 AjaxLoginProcessingFilter 和token拦截器 JwtTokenAuthenticationProcessingFilter
     ```
     @Override
         protected void configure(HttpSecurity http) throws Exception {
             http
             .csrf().disable() // We don't need CSRF for JWT based authentication
     //        .cors().disable()
             .exceptionHandling()
             .authenticationEntryPoint(this.authenticationEntryPoint)
             .and()
                 .sessionManagement()
                 .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
     
             .and()
                 .authorizeRequests()
                     .antMatchers(tokenConfigUrlSettings.getLoginUrl()).permitAll() // Login end-point
                     .antMatchers(tokenConfigUrlSettings.getTokenRefresh()).permitAll() // Token refresh end-point
                     .antMatchers("/console").permitAll() // H2 Console Dash-board - only for testing
                     .antMatchers("/v2/api-docs",//swagger api json
                             "/configuration/ui",//用来获取支持的动作
                             "/swagger-resources",//用来获取api-docs的URI
                             "/configuration/security",//安全选项
                             "/swagger-ui.html").permitAll()
             .and()
                 .authorizeRequests()
                     .antMatchers(tokenConfigUrlSettings.getCheckUrl()).authenticated() // Protected API End-points
             .and()
     //            .addFilterBefore(new SimpleCORSFilter(), ChannelProcessingFilter.class)
     
                 .addFilterBefore(buildAjaxLoginProcessingFilter(), UsernamePasswordAuthenticationFilter.class)
                 .addFilterBefore(buildJwtTokenAuthenticationProcessingFilter(), UsernamePasswordAuthenticationFilter.class);
     
         }
     ```
     这些配置在yml中可见
     具体过程：登录url：/api/auth/login 参数username,password，
     校验成功后，会返回给client一个token时效是60分钟
     然后，client每次请求都要吧token携带在请求头中 字段名是：Authorization，格式是Bearer +后台生成的自定义base64字符串
     用户信息保存在SecurityContext，上下文中，但是前后分离之后，这种上下文使用不如缓存好。
     
     ```
     //获取当前登录用户信息
        UserContext userContext = (UserContext)SecurityContextHolder.getContext()
            .getAuthentication()
            .getPrincipal();
     //获取当前登录用户权限,此处被废弃掉？？？
     ```
     ***
     ###controller和dao层
     - 剩下的就比较简单了，rest风格的api，和jpa原生的dao操作，如果复杂的sql，可以写sql执行，返回类型使用map
     ```
             Query query = entityManager.createNativeQuery(sql);
             query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
             List rows = query.getResultList();
             if (rows != null) {
                 return (Map<String, String>) rows.get(0);
             }
             return null;
     ```
#### tips
 - 文件下载的url不校验token，因为ajax下载很难做到，只有用普通的get请求去下载，content-type设置为文件流，这点在yml中也有体现。好多上线网站有这种问题，
   例如某直播，可以获取数据流不经过token校验，解决思路，先请求下载key，然后get请求拿key给服务端对比后下载，key时效20秒，用完就清掉。
   ***
   ok!到此结束。
   需要扩展的是 权限控制，没有用security的体系，自己实现也比较简单，加入缓存。