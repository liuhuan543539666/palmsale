package com.guoanfamily.palmsale.controller;

import com.guoanfamily.palmsale.security.auth.JwtAuthenticationToken;
import com.guoanfamily.palmsale.security.model.UserContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * End-point for retrieving logged-in user details.
 * 
 * @author vladimir.stankovic
 *
 * Aug 4, 2016
 */
@RestController
public class ProfileEndpoint {
    @RequestMapping(value="/api/me", method= RequestMethod.GET)
    public @ResponseBody
    UserContext get(JwtAuthenticationToken token) {
        return (UserContext) token.getPrincipal();
    }


    @RequestMapping(value="/api/auth/user", method= RequestMethod.GET)
    public String username(JwtAuthenticationToken token) {
        return "ok";
    }
}
