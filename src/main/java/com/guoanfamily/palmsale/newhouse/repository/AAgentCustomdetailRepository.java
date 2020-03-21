package com.guoanfamily.palmsale.newhouse.repository;

import com.guoanfamily.palmsale.newhouse.entity.A_agentcustomdetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by Administrator on 2017/5/27.
 */
public interface AAgentCustomdetailRepository extends JpaRepository<A_agentcustomdetail,String> {
    @Query(value = "select a from A_agentcustomdetail a where a.custphonenumber=:custphonenumber")
    public A_agentcustomdetail  findByPhonenumber(@Param("custphonenumber") String custphonenumber);
}
