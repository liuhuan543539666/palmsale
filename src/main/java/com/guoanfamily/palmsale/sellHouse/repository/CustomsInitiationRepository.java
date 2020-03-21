package com.guoanfamily.palmsale.sellHouse.repository;

import com.guoanfamily.palmsale.sellHouse.entity.Initiationinfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;


public interface CustomsInitiationRepository extends JpaRepository<Initiationinfo, String> , JpaSpecificationExecutor{

}
