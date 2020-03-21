package com.guoanfamily.palmsale.system.repository;

import com.guoanfamily.palmsale.system.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * Created by lenovo on 2017/5/16.
 */
public interface RoleRepository extends JpaRepository<Role, String> {
//    @Query("select r from Role r left join fetch r.users u where u.username=:username")
    public Optional<Role> findByRolename(@Param("rolename") String rolename);

//    @Query(value = "select r from Role r left join MenuFunction m where m.id = :functionid" ,nativeQuery = true)
//    public Optional<List<Role>> findByMenuSetIs(@Param("functionid") String functionid);
}
