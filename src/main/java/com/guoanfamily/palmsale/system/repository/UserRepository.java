package com.guoanfamily.palmsale.system.repository;

import com.guoanfamily.palmsale.system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by lenovo on 2017/5/16.
 */
@Repository
public interface UserRepository extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {
    @Query("select u from User u left join fetch u.roles r where u.username=:username")
    public Optional<User> findByUsername(@Param("username") String username);

    @Query("select u from User u where u.username=:username")
    public List<User> findByGetUsername(@Param("username") String username);

//    @Query(value = "select u from User u left join fetch u.roles r where r.roleid = :roleid" ,nativeQuery = true)
//    public List<User> getUsersByRoleid(@Param("roleid") String roleid);
}
