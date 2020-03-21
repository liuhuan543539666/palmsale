package com.guoanfamily.palmsale.sellHouse.repository;

import com.guoanfamily.palmsale.sellHouse.entity.Share;
import com.guoanfamily.palmsale.sellHouse.model.ShareModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface ShareRepository extends JpaRepository<Share, String> , JpaSpecificationExecutor{
    @Query("SELECT sum(CASE WHEN u.sharetype = 0 THEN 1 ELSE 0 END ) AS common, sum( CASE WHEN u.sharetype = 1 THEN 1 ELSE 0 END ) AS  all, sum( CASE WHEN u.sharetype = 2 THEN 1 ELSE 0 END ) AS trade FROM Share u where  u.businessid=:businessid")
    public ShareModel getStatistics(@Param("businessid")String businessid);
}
