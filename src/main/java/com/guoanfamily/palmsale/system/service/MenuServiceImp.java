package com.guoanfamily.palmsale.system.service;

import com.guoanfamily.palmsale.system.entity.MenuFunction;
import com.guoanfamily.palmsale.system.entity.Role;
import com.guoanfamily.palmsale.system.repository.MenuRepository;
import com.guoanfamily.palmsale.system.repository.RoleRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Created by lenovo on 2017/5/16.
 */
@Service
public class MenuServiceImp implements MenuService{

    private final RoleRepository roleRepository;
    private final MenuRepository menuRepository;

    public MenuServiceImp(RoleRepository roleRepository, MenuRepository menuRepository) {
        this.roleRepository = roleRepository;
        this.menuRepository = menuRepository;
    }

    @Transactional
    @Override
    public void removeMentAndRole(String id) throws Exception{
        MenuFunction menuFunction = menuRepository.findOne(id);
        if (null != menuFunction){
            Set<Role> roleSet = menuFunction.getRoleSet();
            if (null != roleSet && !roleSet.isEmpty()){
                for (Role role : roleSet) {
                    role.removeMenuFuction(menuFunction);
                }
                roleRepository.save(roleSet);
            }
            menuRepository.delete(id);
        }
    }
}
