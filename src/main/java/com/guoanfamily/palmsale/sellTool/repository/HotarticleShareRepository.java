package com.guoanfamily.palmsale.sellTool.repository;

import com.guoanfamily.palmsale.sellTool.entity.HotarticleShare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Administrator on 2017/5/26.
 */
public interface HotarticleShareRepository extends JpaRepository<HotarticleShare, String>,JpaSpecificationExecutor {

}
