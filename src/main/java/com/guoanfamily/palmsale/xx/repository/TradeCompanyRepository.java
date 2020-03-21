package com.guoanfamily.palmsale.xx.repository;

import com.guoanfamily.palmsale.xx.entity.TradeCompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * trade_company表操作
 *
 * @author 张文旭
 * @version 1.0
 * @date 2017-06-21 09:11:19
 */
public interface TradeCompanyRepository extends JpaRepository<TradeCompanyEntity, String> , JpaSpecificationExecutor{

}
