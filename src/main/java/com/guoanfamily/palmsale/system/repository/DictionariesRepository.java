package com.guoanfamily.palmsale.system.repository;

import com.guoanfamily.palmsale.system.entity.DictionariesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by lenovo on 2017/5/16.
 */
public interface DictionariesRepository extends JpaRepository<DictionariesEntity, String> {

    public List<DictionariesEntity> findBypid(String pid);

}
