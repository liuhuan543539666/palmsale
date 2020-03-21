package com.guoanfamily.palmsale.newhouse.repository;

import com.guoanfamily.palmsale.newhouse.entity.Bookscanbuilding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

/**
 * Created by Administrator on 2017/5/19.
 */
public interface BookScanBuildingRepository extends JpaRepository<Bookscanbuilding,String>{
    @Transactional
    @Modifying
    @Query(value = "update bookscanbuilding b set b.channeltype=?1,b.actvisiter=?2,b.actcustomname=?3,b.actvistitcount=?4,b.update_date=?5,b.remark=?6,b.state=?7 where b.phonenum=?8",nativeQuery = true)
     void updateBookscnbuildByPhonenum(String channeltype, String actvisiter, String actcustomname, String actvistitcount, Date update_date, String remark, String state, String custphone);
}
