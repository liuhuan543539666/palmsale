package com.guoanfamily.palmsale.sellHouse.repository;

import com.guoanfamily.palmsale.sellHouse.entity.CustomerDealInfo;
import com.guoanfamily.palmsale.sellHouse.entity.PayMoneyRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Administrator on 2017/6/1.
 */

public interface PayMoneyRepository extends JpaRepository<CustomerDealInfo, String>, JpaSpecificationExecutor {

}

