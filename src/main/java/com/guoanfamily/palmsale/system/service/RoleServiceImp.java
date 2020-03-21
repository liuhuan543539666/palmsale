package com.guoanfamily.palmsale.system.service;

import com.guoanfamily.palmsale.system.entity.Role;
import com.guoanfamily.palmsale.system.entity.User;
import com.guoanfamily.palmsale.system.repository.RoleRepository;
import com.guoanfamily.palmsale.system.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

/**
 * Created by lenovo on 2017/5/16.
 */
@Service
public class RoleServiceImp implements RoleService{
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public RoleServiceImp(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public void removeRoleAndMenu(String id) {
        Role role =  roleRepository.findOne(id);
        if(null != role){
            Set<User> users = role.getUsers();
            if(null != users && !users.isEmpty()){
                for (User user : users) {
                    user.removeRole(role);
                }
                userRepository.save(users);
            }
            role.setMenuSet(null);
            roleRepository.save(role);
            roleRepository.delete(id);
        }
    }
}
