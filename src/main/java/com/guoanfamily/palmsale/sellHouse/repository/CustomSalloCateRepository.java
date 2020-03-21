package com.guoanfamily.palmsale.sellHouse.repository;
import com.guoanfamily.palmsale.sellHouse.entity.CustomSalloCate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/5/5.
 */

public interface CustomSalloCateRepository extends JpaRepository<CustomSalloCate, String>, JpaSpecificationExecutor {

//    @Query("select c from CustomSalloCate c where c.agentid =:agentid")
//    public List<CustomSalloCate> getCustomSalloCate(@Param("agentid")String agentid);

}
