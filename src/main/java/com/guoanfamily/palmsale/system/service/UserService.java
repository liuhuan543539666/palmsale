package com.guoanfamily.palmsale.system.service;

import com.guoanfamily.palmsale.system.entity.User;

import java.util.Optional;

/**
 * 
 * @author vladimir.stankovic
 *
 * Aug 17, 2016
 */
public interface UserService {
    public Optional<User> getByUsername(String username);
    public String loginCaptcha(String randomCode, String phoneNumber);
    public User userSaveToRole(User user);
}
