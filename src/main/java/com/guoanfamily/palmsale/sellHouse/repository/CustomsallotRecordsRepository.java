package com.guoanfamily.palmsale.sellHouse.repository;

import com.guoanfamily.palmsale.sellHouse.entity.CustomsallotRecords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface CustomsallotRecordsRepository extends JpaRepository<CustomsallotRecords, String> , JpaSpecificationExecutor{

}
