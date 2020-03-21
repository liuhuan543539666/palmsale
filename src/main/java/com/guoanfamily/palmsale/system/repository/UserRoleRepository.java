package com.guoanfamily.palmsale.system.repository;

import com.guoanfamily.palmsale.system.entity.Role;
import com.guoanfamily.palmsale.system.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * Created by lenovo on 2017/5/16.
 */
public interface UserRoleRepository extends JpaRepository<UserRole, String> {

}
