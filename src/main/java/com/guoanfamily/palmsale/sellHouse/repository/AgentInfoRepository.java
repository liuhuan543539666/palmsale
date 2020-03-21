package com.guoanfamily.palmsale.sellHouse.repository;

import com.guoanfamily.palmsale.sellHouse.entity.AgentInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface AgentInfoRepository extends JpaRepository<AgentInfo, String> , JpaSpecificationExecutor{

}
