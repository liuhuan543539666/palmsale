package com.guoanfamily.palmsale.sellTool.repository;

import com.guoanfamily.palmsale.sellTool.entity.Hotarticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Administrator on 2017/5/25.
 */
public interface HotarticleRepository extends JpaRepository<Hotarticle, String>,JpaSpecificationExecutor {

}
