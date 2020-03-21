package com.guoanfamily.palmsale.newhouse.repository;

import com.guoanfamily.palmsale.newhouse.entity.NCustomsallocate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Created by Administrator on 2017/5/19.
 */
public interface NCustomsallocateRepository extends JpaRepository<NCustomsallocate ,String>,JpaSpecificationExecutor<NCustomsallocate>{
    @Transactional
    @Modifying
    @Query(value = "update n_customsallocate n set n.userid=?1 where n.id=?2",nativeQuery = true)
     void updateNCustomsallocateById(String sid, String cid);

    @Query(value = "select n from NCustomsallocate n where n.phonenumber=:phonenumber")
    public NCustomsallocate  findByPhonenumber(@Param("phonenumber") String phonenumber);
}
