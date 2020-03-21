package com.guoanfamily.palmsale.sellHouse.repository;

import com.guoanfamily.palmsale.sellHouse.entity.PayMoneyRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Administrator on 2017/6/1.
 */

public interface PayMoneyRecordRepository extends JpaRepository<PayMoneyRecord, String>, JpaSpecificationExecutor {

}

