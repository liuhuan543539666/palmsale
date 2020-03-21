package com.guoanfamily.palmsale.security.auth.jwt;

import com.guoanfamily.palmsale.security.auth.JwtAuthenticationToken;
import com.guoanfamily.palmsale.security.auth.jwt.extractor.TokenExtractor;
import com.guoanfamily.palmsale.security.config.TokenConfigUrlSettings;
import com.guoanfamily.palmsale.security.config.WebSecurityConfig;
import com.guoanfamily.palmsale.security.model.UserContext;
import com.guoanfamily.palmsale.security.model.token.RawAccessJwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Performs validation of provided JWT Token.
 * 
 * @author vladimir.stankovic
 *
 * Aug 5, 2016
 */
public class JwtTokenAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {
    private final AuthenticationFailureHandler failureHandler;
    private final TokenExtractor tokenExtractor;
    private final TokenConfigUrlSettings tokenConfigUrlSettings;
    @Autowired
    public JwtTokenAuthenticationProcessingFilter(AuthenticationFailureHandler failureHandler,
                                                  TokenExtractor tokenExtractor, RequestMatcher matcher, TokenConfigUrlSettings tokenConfigUrlSettings) {
        super(matcher);
        this.failureHandler = failureHandler;
        this.tokenExtractor = tokenExtractor;
        this.tokenConfigUrlSettings = tokenConfigUrlSettings;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        if(HttpMethod.OPTIONS.name().equals(request.getMethod()) ){
//            response.setStatus(HttpStatus.OK.value());
            String origin = request.getHeader("Origin");
            response.setHeader("Access-Control-Allow-Origin", origin);
            response.setHeader("Access-Control-Allow-Headers", "accept,authorization,content-type");
            return null;
        }
        String tokenPayload = request.getHeader(tokenConfigUrlSettings.getHeaderParam());
//        if("swagger".equals(tokenPayload)){
//            response.setStatus(HttpStatus.OK.value());
//            return null;
//        }
        RawAccessJwtToken token = new RawAccessJwtToken(tokenExtractor.extract(tokenPayload));
        return getAuthenticationManager().authenticate(new JwtAuthenticationToken(token));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authResult);
        SecurityContextHolder.setContext(context);
        JwtAuthenticationToken jwt = (JwtAuthenticationToken)authResult;
        UserContext userContext = (UserContext)jwt.getPrincipal();
        String userId = userContext.getUserId();
        request.setAttribute("userId", userId);
        chain.doFilter(request, response);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        SecurityContextHolder.clearContext();
        failureHandler.onAuthenticationFailure(request, response, failed);
    }
}
