package com.guoanfamily.palmsale.sellHouse.repository;

import com.guoanfamily.palmsale.sellHouse.entity.Browse;
import com.guoanfamily.palmsale.sellHouse.entity.CustomerDealInfo;
import com.guoanfamily.palmsale.sellHouse.model.BrowseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface BrowseRepository extends JpaRepository<Browse, String> , JpaSpecificationExecutor{
    @Query(value = "SELECT  sum( CASE WHEN u.browsetype = 0 THEN 1 ELSE 0 END ) AS consultant, sum( CASE WHEN u.browsetype = 1 THEN 1 ELSE 0 END ) AS  other FROM p_browse u where  u.businessid=:businessid",nativeQuery = true)
    public  BrowseModel getStatistics(@Param("businessid")String businessid);
}
