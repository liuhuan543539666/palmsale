package com.guoanfamily.palmsale.sellHouse.repository;

import com.guoanfamily.palmsale.sellHouse.entity.CustomsHistoryGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface CustomsHistoryGroupByRepository extends JpaRepository<CustomsHistoryGroup, String> , JpaSpecificationExecutor{
}
