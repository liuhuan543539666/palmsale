package com.guoanfamily.palmsale.security.endpoint;

import com.guoanfamily.palmsale.common.AjaxJson;
import com.guoanfamily.palmsale.security.auth.jwt.extractor.TokenExtractor;
import com.guoanfamily.palmsale.security.auth.jwt.verifier.TokenVerifier;
import com.guoanfamily.palmsale.security.config.JwtSettings;
import com.guoanfamily.palmsale.security.config.TokenConfigUrlSettings;
import com.guoanfamily.palmsale.security.config.WebSecurityConfig;
import com.guoanfamily.palmsale.security.exceptions.InvalidJwtToken;
import com.guoanfamily.palmsale.security.model.UserContext;
import com.guoanfamily.palmsale.security.model.token.JwtTokenFactory;
import com.guoanfamily.palmsale.security.model.token.RawAccessJwtToken;
import com.guoanfamily.palmsale.security.model.token.RefreshToken;
import com.guoanfamily.palmsale.system.entity.User;
import com.guoanfamily.palmsale.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * RefreshTokenEndpoint
 * 
 * @author vladimir.stankovic
 *
 * Aug 17, 2016
 */
@RestController
public class RefreshTokenEndpoint {

    private final TokenConfigUrlSettings tokenConfigUrlSettings;
    @Autowired
    private JwtTokenFactory tokenFactory;
    @Autowired
    private JwtSettings jwtSettings;
    @Autowired
    private UserService userService;
    @Autowired
    private TokenVerifier tokenVerifier;
    @Autowired
    @Qualifier("jwtHeaderTokenExtractor") private TokenExtractor tokenExtractor;

    public RefreshTokenEndpoint(TokenConfigUrlSettings tokenConfigUrlSettings) {
        this.tokenConfigUrlSettings = tokenConfigUrlSettings;
    }

    @RequestMapping(value="/api/auth/token", method= RequestMethod.GET, produces={ MediaType.APPLICATION_JSON_VALUE })
    public @ResponseBody
    AjaxJson refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String tokenPayload = tokenExtractor.extract(request.getHeader(tokenConfigUrlSettings.getHeaderParam()));
        
        RawAccessJwtToken rawToken = new RawAccessJwtToken(tokenPayload);
        RefreshToken refreshToken = RefreshToken.create(rawToken, jwtSettings.getTokenSigningKey()).orElseThrow(() -> new InvalidJwtToken());

        String jti = refreshToken.getJti();
        if (!tokenVerifier.verify(jti)) {
            throw new InvalidJwtToken();
        }

        String subject = refreshToken.getSubject();
        User user = userService.getByUsername(subject).orElseThrow(() -> new UsernameNotFoundException("User not found: " + subject));

        if (user.getRoles() == null) throw new InsufficientAuthenticationException("User has no roles assigned");
        Set<GrantedAuthority> authorities = user.getRoles().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.authority()))
                .collect(Collectors.toSet());

        UserContext userContext = UserContext.create(user.getId(), user.getUsername(), authorities);

        AjaxJson ajaxJson = new AjaxJson();
        return ajaxJson.setSuccess(true).setStatus(200).setData(tokenFactory.createAccessJwtToken(userContext));
    }
}
