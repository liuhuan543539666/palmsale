package com.guoanfamily.palmsale.sellHouse.repository;

import com.guoanfamily.palmsale.sellHouse.entity.BuildbaseinfoDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface BuildBaseInfoDetailRepository extends JpaRepository<BuildbaseinfoDetail, String>, JpaSpecificationExecutor{

}
