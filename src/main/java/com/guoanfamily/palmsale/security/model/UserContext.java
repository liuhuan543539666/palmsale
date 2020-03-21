package com.guoanfamily.palmsale.security.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

/**
 * 
 * @author vladimir.stankovic
 *
 * Aug 4, 2016
 */
@Getter
@AllArgsConstructor
public class UserContext {

    private final String userId;
    private final String username;
    private final Set<GrantedAuthority> authorities;

//    private UserContext(String username, Set<GrantedAuthority> authorities) {
//        this.username = username;
//        this.authorities = authorities;
//    }
    
    public static UserContext create(String userId, String username, Set<GrantedAuthority> authorities) {
        if (StringUtils.isBlank(username)) throw new IllegalArgumentException("Username is blank: " + username);
        if (StringUtils.isBlank(userId)) throw new IllegalArgumentException("UserId is blank: " + userId);
        return new UserContext(userId, username, authorities);
    }

//    public String getUsername() {
//        return username;
//    }
//
//    public Set<GrantedAuthority> getAuthorities() {
//        return authorities;
//    }
}
