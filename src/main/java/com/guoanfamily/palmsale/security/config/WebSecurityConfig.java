package com.guoanfamily.palmsale.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.guoanfamily.palmsale.security.RestAuthenticationEntryPoint;
import com.guoanfamily.palmsale.security.auth.ajax.AjaxAuthenticationProvider;
import com.guoanfamily.palmsale.security.auth.ajax.AjaxLoginProcessingFilter;
import com.guoanfamily.palmsale.security.auth.jwt.JwtAuthenticationProvider;
import com.guoanfamily.palmsale.security.auth.jwt.JwtTokenAuthenticationProcessingFilter;
import com.guoanfamily.palmsale.security.auth.jwt.SkipPathRequestMatcher;
import com.guoanfamily.palmsale.security.auth.jwt.extractor.TokenExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 
 * @author vladimir.stankovic
 *
 * Aug 3, 2016
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//    public static final String FORM_BASED_LOGIN_ENTRY_POINT = "/api/auth/login";
//    public static final String EXCEPTION_URL = "/admin/**";
//    public static final String TOKEN_BASED_AUTH_ENTRY_POINT = "/api/**";
//    public static final String TOKEN_REFRESH_ENTRY_POINT = "/api/auth/token";

    private final TokenConfigUrlSettings tokenConfigUrlSettings;

    @Autowired
    private RestAuthenticationEntryPoint authenticationEntryPoint;
    @Autowired
    private AuthenticationSuccessHandler successHandler;
    @Autowired
    private AuthenticationFailureHandler failureHandler;
    @Autowired
    private AjaxAuthenticationProvider ajaxAuthenticationProvider;
    @Autowired
    private JwtAuthenticationProvider jwtAuthenticationProvider;
    
    @Autowired
    private TokenExtractor tokenExtractor;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private ObjectMapper objectMapper;

    public WebSecurityConfig(TokenConfigUrlSettings tokenConfigUrlSettings) {
        this.tokenConfigUrlSettings = tokenConfigUrlSettings;
    }

    protected AjaxLoginProcessingFilter buildAjaxLoginProcessingFilter() throws Exception {
        AjaxLoginProcessingFilter filter = new AjaxLoginProcessingFilter(tokenConfigUrlSettings.getLoginUrl(), successHandler, failureHandler, objectMapper);
        filter.setAuthenticationManager(this.authenticationManager);
        return filter;
    }
    
    protected JwtTokenAuthenticationProcessingFilter buildJwtTokenAuthenticationProcessingFilter() throws Exception {
        List<String> pathsToSkip = new ArrayList<String>();
        pathsToSkip.add(tokenConfigUrlSettings.getTokenRefresh());
        pathsToSkip.add(tokenConfigUrlSettings.getLoginUrl());
        String[] nonChenclUrls = tokenConfigUrlSettings.getNonCheckUrl();
        if (null != nonChenclUrls && nonChenclUrls.length > 0){
            for (String nonChenclUrl : nonChenclUrls) {
                pathsToSkip.add(nonChenclUrl);
            }
        }
        SkipPathRequestMatcher matcher = new SkipPathRequestMatcher(pathsToSkip, tokenConfigUrlSettings.getCheckUrl());
        JwtTokenAuthenticationProcessingFilter filter 
            = new JwtTokenAuthenticationProcessingFilter(failureHandler, tokenExtractor, matcher, tokenConfigUrlSettings);
        filter.setAuthenticationManager(this.authenticationManager);
        return filter;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(ajaxAuthenticationProvider);
        auth.authenticationProvider(jwtAuthenticationProvider);
    }

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
                .antMatchers("/**").permitAll() // H2 Console Dash-board - only for testing
                .antMatchers("/v2/api-docs",//swagger api json
                        "/configuration/ui",//用来获取支持的动作
                        "/swagger-resources",//用来获取api-docs的URI
                        "/configuration/security",//安全选项
                        "/swagger-ui.html").permitAll()
        .and()
            .authorizeRequests()
                .antMatchers(tokenConfigUrlSettings.getCheckUrl()).authenticated() // Protected API End-points
        .and()
            .addFilterBefore(buildAjaxLoginProcessingFilter(), UsernamePasswordAuthenticationFilter.class)
            .addFilterBefore(buildJwtTokenAuthenticationProcessingFilter(), UsernamePasswordAuthenticationFilter.class);

    }
}
