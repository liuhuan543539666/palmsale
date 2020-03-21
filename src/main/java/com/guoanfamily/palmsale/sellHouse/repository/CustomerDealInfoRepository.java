package com.guoanfamily.palmsale.sellHouse.repository;
import com.guoanfamily.palmsale.sellHouse.entity.CustomerDealInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/5/10.
 */
public interface CustomerDealInfoRepository extends JpaRepository<CustomerDealInfo, String> ,JpaSpecificationExecutor {

    @Query("select p from CustomerDealInfo p where p.customSalloCate.id =:customerid")
    public List<CustomerDealInfo> getCustomSalloCate(@Param("customerid")String customerid);

//    @Query("insert into user (customerid, housenumber,unitnumber,roomnumber,towards,totalprices,buidingarea,createtime,updatetime) values (?,?,?,?,?,?,?)");
//    public void addCustomerDealInfo(String customerid,String housenumber,String unitnumber,String roomnumber,String towards,String totalprices,String buidingarea,java.sql.Timestamp createtime,java.sql.Timestamp updatetime);
}
