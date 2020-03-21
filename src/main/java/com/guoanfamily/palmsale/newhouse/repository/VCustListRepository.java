package com.guoanfamily.palmsale.newhouse.repository;

import com.guoanfamily.palmsale.newhouse.entity.VCustList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by Administrator on 2017/5/17.
 */

public interface VCustListRepository extends JpaRepository<VCustList, String>, JpaSpecificationExecutor<VCustList> {
}
