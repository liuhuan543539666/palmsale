package com.guoanfamily.palmsale.sellHouse.repository;

import com.guoanfamily.palmsale.sellHouse.entity.Customsllocate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;


public interface CustomsllocateRepository extends JpaRepository<Customsllocate, String> , JpaSpecificationExecutor{
    public Page<Customsllocate> findByCustname(@Param("custname") String custname, Pageable page);

    @Query("select u from Customsllocate u where u.agentinfo.id=:agentid")
    public List<Customsllocate> findAgentId(@Param("agentid") String agentid);

    @Query("select u from Customsllocate u where u.custAnalyzeDict.dictname=:intentionlevel")
    public Page<Customsllocate> findIntentionlevel(@Param("intentionlevel") String intentionlevel, Pageable page);
}