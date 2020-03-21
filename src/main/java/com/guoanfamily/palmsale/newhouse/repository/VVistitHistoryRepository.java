package com.guoanfamily.palmsale.newhouse.repository;

import com.guoanfamily.palmsale.newhouse.entity.V_visit_history;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/5/19.
 */
public interface VVistitHistoryRepository extends JpaRepository<V_visit_history,String> , JpaSpecificationExecutor<V_visit_history> {
    @Query("select v  from V_visit_history v  where v.phonenumber=:phonenumber")
    List<V_visit_history> findAllByPhonenumber(@Param("phonenumber") String phonenumber);
}
