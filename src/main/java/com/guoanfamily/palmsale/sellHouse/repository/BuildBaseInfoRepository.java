package com.guoanfamily.palmsale.sellHouse.repository;

import com.guoanfamily.palmsale.sellHouse.entity.Buildbaseinfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface BuildBaseInfoRepository extends JpaRepository<Buildbaseinfo, String> , JpaSpecificationExecutor{

}
