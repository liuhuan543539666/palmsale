package com.guoanfamily.palmsale.config;

import com.fasterxml.classmate.TypeResolver;
import com.guoanfamily.palmsale.security.auth.jwt.extractor.JwtHeaderTokenExtractor;
import com.guoanfamily.palmsale.security.model.UserContext;
import com.guoanfamily.palmsale.security.model.token.JwtTokenFactory;
import com.guoanfamily.palmsale.system.entity.User;
import com.guoanfamily.palmsale.system.service.UserService;
import lombok.AllArgsConstructor;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.*;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Set;
import java.util.stream.Collectors;

import static com.google.common.collect.Lists.newArrayList;
import static springfox.documentation.schema.AlternateTypeRules.newRule;

/**
 * @author cuiss
 * @version 1.0.0
 * @date 17/3/34 下午3:19.
 */

@AllArgsConstructor
@Configuration
@EnableSwagger2
public class Swagger2 {


    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(apiInfo())
//                .select()
//                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.any())
//                .build();
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/")
                .directModelSubstitute(LocalDate.class,
                        String.class)
                .genericModelSubstitutes(ResponseEntity.class)
                .alternateTypeRules(
                        newRule(typeResolver.resolve(DeferredResult.class,
                                typeResolver.resolve(ResponseEntity.class, WildcardType.class)),
                                typeResolver.resolve(WildcardType.class)))
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET,
                        newArrayList(new ResponseMessageBuilder()
                                .code(500)
                                .message("500 message")
                                .responseModel(new ModelRef("Error"))
                                .build()))
                .securitySchemes(newArrayList(apiKey()))
//                .securityContexts(newArrayList(securityContext()))
                .enableUrlTemplating(false)
//                .globalOperationParameters(
//                        newArrayList(new ParameterBuilder()
//                                .name("someGlobalParameter")
//                                .description("Description of someGlobalParameter")
//                                .modelRef(new ModelRef("string"))
//                                .parameterType("query")
//                                .required(true)
//                                .build()))
                .tags(new Tag("Pet Service", "All apis relating to pets"))
//                .additionalModels(typeResolver.resolve(AdditionalModel.class))
                ;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("合伙人申请管理 API")
                .description("地产行业经纪人端业务")
                .termsOfServiceUrl("")
                .version("1.0")
                .build();
    }

//    private String tokenString (){
//
//        User user = userService.getByUsername("admin").orElseThrow(() -> new UsernameNotFoundException("User not found: admin"));
//
//        if (user.getRoles() == null) throw new InsufficientAuthenticationException("User has no roles assigned");
//        Set<GrantedAuthority> authorities = user.getRoles().stream()
//                .map(authority -> new SimpleGrantedAuthority(authority.authority()))
//                .collect(Collectors.toSet());
//
//        UserContext userContext = UserContext.create(user.getId(), user.getUsername(), authorities);
//
//        return JwtHeaderTokenExtractor.HEADER_PREFIX + tokenFactory.createAccessJwtToken(userContext).getToken();
//    }

    @Autowired
    private TypeResolver typeResolver;

    private ApiKey apiKey() {
        return new ApiKey("token", "token", "header");
    }

//    private SecurityContext securityContext() {
//        return SecurityContext.builder()
//                .securityReferences(defaultAuth())
//                .forPaths(PathSelectors.regex("/api/*"))
//                .build();
//    }

//    List<SecurityReference> defaultAuth() {
//        AuthorizationScope authorizationScope
//                = new AuthorizationScope("global", "accessEverything");
//        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
//        authorizationScopes[0] = authorizationScope;
//        return newArrayList(
//                new SecurityReference("mykey", authorizationScopes));
//    }

    @Bean
    SecurityConfiguration security() {
        return new SecurityConfiguration(
                "test-app-client-id",
                "test-app-client-secret",
                "test-app-realm",
                "test-app",
                JwtHeaderTokenExtractor.HEADER_PREFIX + JwtHeaderTokenExtractor.SWAGGER_TOKEN,
                ApiKeyVehicle.HEADER,
                "Authorization",
                "," /*scope separator*/);
    }

//    @Bean
//    public SecurityConfiguration sfoxSecurityConfiguration() {
//        return new SecurityConfiguration("123", "abc", "test", "123456");
//    }

    @Bean
    UiConfiguration uiConfig() {
        return new UiConfiguration(
                "validatorUrl",// url
                "none",       // docExpansion          => none | list
                "alpha",      // apiSorter             => alpha
                "schema",     // defaultModelRendering => schema
                UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS,
                false,        // enableJsonEditor      => true | false
                true,         // showRequestHeaders    => true | false
                60000L);      // requestTimeout => in milliseconds, defaults to null (uses jquery xh timeout)
    }
}
