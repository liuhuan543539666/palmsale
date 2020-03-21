package com.guoanfamily.palmsale.newhouse.repository;

import com.guoanfamily.palmsale.newhouse.entity.V_follow_history;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by Administrator on 2017/5/19.
 */
public interface VFollowHistoryRepository extends JpaRepository<V_follow_history,String> {
    @Query("select  follow from V_follow_history  follow where follow.phonenumber=:phonenumber")
    List<V_follow_history> findAllByPhonenumber(@Param("phonenumber") String phonenumber);

}
