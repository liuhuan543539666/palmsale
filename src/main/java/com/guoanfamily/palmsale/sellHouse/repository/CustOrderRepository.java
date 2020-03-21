package com.guoanfamily.palmsale.sellHouse.repository;

import com.guoanfamily.palmsale.sellHouse.entity.CustOrder;
import com.guoanfamily.palmsale.sellHouse.entity.Customsllocate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface CustOrderRepository extends JpaRepository<CustOrder, String> , JpaSpecificationExecutor{
    public CustOrder findById(String id);

    public CustOrder findByCustphonenumberAndHouseresourceid(String custphonenumber,String houseresourceid);

    public CustOrder findByHouseresourceid(String houseresourceid);
}
