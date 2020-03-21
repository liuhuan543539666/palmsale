package com.guoanfamily.palmsale.system.repository;

import com.guoanfamily.palmsale.system.entity.MenuFunction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by lenovo on 2017/5/16.
 */
public interface MenuRepository extends JpaRepository<MenuFunction, String> {

    public List<MenuFunction> findByparent(String parentfunctionid);

    @Query("select max(m.level) from MenuFunction m")
    public Integer getMaxLevel();
}
