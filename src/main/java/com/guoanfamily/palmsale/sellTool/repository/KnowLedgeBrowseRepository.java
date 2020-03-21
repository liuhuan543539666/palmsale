package com.guoanfamily.palmsale.sellTool.repository;

import com.guoanfamily.palmsale.sellTool.entity.KnowLedgeBrowse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Administrator on 2017/5/22.
 */
public interface KnowLedgeBrowseRepository extends JpaRepository<KnowLedgeBrowse, String>,JpaSpecificationExecutor{

}
