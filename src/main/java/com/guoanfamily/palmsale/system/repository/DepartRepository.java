package com.guoanfamily.palmsale.system.repository;

import com.guoanfamily.palmsale.system.entity.Depart;
import com.guoanfamily.palmsale.system.entity.DictionariesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by lenovo on 2017/5/16.
 */
public interface DepartRepository extends JpaRepository<Depart, String> {

    public List<Depart> findByparentdepartid(String parentdepartid);

    @Query("select max(d.org_code) from Depart d where d.parentdepartid is null")
    public String findMaxOrgCode();

    @Query("select max(d.org_code) from Depart d where d.parentdepartid=:parentDepartId")
    public String findMaxOrgCodeByPid(@Param(value = "parentDepartId") String parentDepartId);
}
