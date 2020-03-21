package com.guoanfamily.palmsale.system.service;

import com.guoanfamily.palmsale.common.util.MessageUtils;
import com.guoanfamily.palmsale.system.entity.MessageModelEntity;
import com.guoanfamily.palmsale.system.entity.Role;
import com.guoanfamily.palmsale.system.entity.User;
import com.guoanfamily.palmsale.system.entity.UserRole;
import com.guoanfamily.palmsale.system.repository.MessageRepository;
import com.guoanfamily.palmsale.system.repository.RoleRepository;
import com.guoanfamily.palmsale.system.repository.UserRepository;
import com.guoanfamily.palmsale.system.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Mock implementation.
 * 
 * @author vladimir.stankovic
 *
 * Aug 4, 2016
 */
@Service
public class DatabaseUserService implements UserService {
    private final UserRepository userRepository;

    private final MessageRepository messageRepository;

    private final RoleRepository roleRepository;

    private final UserRoleRepository userRoleRepository;
    @Autowired
    public DatabaseUserService(UserRoleRepository userRoleRepository, UserRepository userRepository, MessageRepository messageRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
        this.roleRepository = roleRepository;
        this.userRoleRepository = userRoleRepository;
    }
    
    public UserRepository getUserRepository() {
        return userRepository;
    }

    @Override
    public Optional<User> getByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public String loginCaptcha(String randomCode, String phoneNumber) {
        MessageModelEntity messageModelEntity = messageRepository.findByName("成为经纪人验证码");
        String msgTemplate = messageModelEntity.getInfo();
        String[] args = {randomCode};
        String msg = MessageUtils.createMessageModel(msgTemplate, args);
        return MessageUtils.messagePostRequest(msg, phoneNumber);
    }

    @Override
    public User userSaveToRole(User user) {
        if (null == user) return user;
        if(user.getId() != null){
            if(user.getRoles() == null){
                Role role = roleRepository.findByRolename("展示中心销售").orElseThrow(() -> new UsernameNotFoundException("Role not found: 展示中心销售"));
                UserRole userRole = new UserRole();
                userRole.setRoleid(role.getId());
                userRole.setUserid(user.getId());
                userRoleRepository.save(userRole);
            }
        }
        return user;
    }
}
