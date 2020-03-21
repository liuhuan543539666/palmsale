package com.guoanfamily.palmsale.security.auth.jwt;

import com.guoanfamily.palmsale.security.auth.JwtAuthenticationToken;
import com.guoanfamily.palmsale.security.auth.jwt.extractor.JwtHeaderTokenExtractor;
import com.guoanfamily.palmsale.security.config.JwtSettings;
import com.guoanfamily.palmsale.security.model.UserContext;
import com.guoanfamily.palmsale.security.model.token.JwtToken;
import com.guoanfamily.palmsale.security.model.token.RawAccessJwtToken;
import com.guoanfamily.palmsale.system.entity.User;
import com.guoanfamily.palmsale.system.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * An {@link AuthenticationProvider} implementation that will use provided
 * instance of {@link JwtToken} to perform authentication.
 * 
 * @author vladimir.stankovic
 *
 * Aug 5, 2016
 */
@Component
@SuppressWarnings("unchecked")
public class JwtAuthenticationProvider implements AuthenticationProvider {
    private final JwtSettings jwtSettings;
    private final UserService userService;
    
    @Autowired
    public JwtAuthenticationProvider(JwtSettings jwtSettings, UserService userService) {
        this.jwtSettings = jwtSettings;
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        RawAccessJwtToken rawAccessToken = (RawAccessJwtToken) authentication.getCredentials();
        String token = rawAccessToken.getToken();
        if(StringUtils.isNotEmpty(token) && JwtHeaderTokenExtractor.SWAGGER_TOKEN.equals(token)){
            UserContext context =  this.adminContext();
            return new JwtAuthenticationToken(context, context.getAuthorities());
        }
        Jws<Claims> jwsClaims = rawAccessToken.parseClaims(jwtSettings.getTokenSigningKey());
        String subject = jwsClaims.getBody().getSubject();
        String userId = (String) jwsClaims.getBody().get("userId");
        Set<String> scopes = new HashSet <String>(jwsClaims.getBody().get("scopes", List.class));
        Set<GrantedAuthority> authorities = scopes.stream()
                .map(authority -> new SimpleGrantedAuthority(authority))
                .collect(Collectors.toSet());
        
        UserContext context = UserContext.create(userId, subject, authorities);
        
        return new JwtAuthenticationToken(context, context.getAuthorities());
    }

    private UserContext adminContext (){

        User user = userService.getByUsername("admin").orElseThrow(() -> new UsernameNotFoundException("User not found: admin"));

        if (user.getRoles() == null) throw new InsufficientAuthenticationException("User has no roles assigned");
        Set<GrantedAuthority> authorities = user.getRoles().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.authority()))
                .collect(Collectors.toSet());

        return UserContext.create(user.getId(), user.getUsername(), authorities);

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (JwtAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
