package com.guoanfamily.palmsale.sellTool.repository;

import com.guoanfamily.palmsale.sellTool.entity.KnowLedge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Administrator on 2017/5/22.
 */
public interface KnowLedgeRepository extends JpaRepository<KnowLedge, String>,JpaSpecificationExecutor{

}
