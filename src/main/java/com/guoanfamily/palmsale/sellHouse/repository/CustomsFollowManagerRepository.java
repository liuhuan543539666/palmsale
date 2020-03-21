package com.guoanfamily.palmsale.sellHouse.repository;

import com.guoanfamily.palmsale.sellHouse.entity.CustomsFollowManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface CustomsFollowManagerRepository extends JpaRepository<CustomsFollowManager, String> , JpaSpecificationExecutor{
    public List<String> findByCustname(@Param("custname") String custname);

    public List<String> findByPhonenumber(@Param("phonenumber") String phonenumber);
}
