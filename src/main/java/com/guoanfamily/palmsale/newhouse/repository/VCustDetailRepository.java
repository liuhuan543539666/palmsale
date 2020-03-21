package com.guoanfamily.palmsale.newhouse.repository;

import com.guoanfamily.palmsale.newhouse.entity.VCustList;
import com.guoanfamily.palmsale.newhouse.entity.VCustdetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Administrator on 2017/5/25.
 */
public interface VCustDetailRepository extends JpaRepository <VCustdetail,String> ,JpaSpecificationExecutor<VCustdetail>{

}
