package com.guoanfamily.palmsale.newhouse.Utils;

/**
 * Created by Administrator on 2017/5/25.
 */
public final class SqlUtils {
    //客户详情信息
    public static String CUST_DETAIL_SQL="SELECT  " +
            "  nc.id AS uid,  " +
            "  nc.custname AS custname,  " +
            "  nc.sex AS sex,  " +
            "  nc.phonenumber as phonenumber,  " +
            "  nc.channeltype AS channeltype,  " +
            "  nc.recommendedtime AS recommendedtime,  " +
            "  tra.cmpname AS cmpname,  " +
            "  agi.agentname,  " +
            "  aus.phonenum AS agentphonenum,  " +
            "  bbi.buildname AS buildname,  " +
            "  nc.custstate AS custstate,  " +
            "  boo.scantime AS scantime,  " +
            "  boo.address AS address,  " +
            "  acd.cardtype AS cardtype,  " +
            "  acd.cardId AS cardId,  " +
            "  acd.custlabel AS custlabel,  " +
            "  acd.othername AS othername,  " +
            "  acd.otherphone AS otherphone,  " +
            "  acd.customerdescription AS remark,  " +
            "  acd.intentionlevel AS intentionlevel,  " +
            "  acd.intentionarea AS intentionare,  " +
            "  acd.intentionfloor AS intentionfloor,  " +
            "  nc.importinfo AS importinfo,  " +
            "  nc.propaim AS propaim,  " +
            "  acd.livearea AS livearea,  " +
            "  acd.workarea AS workarea,  " +
            "  acd.marriagestatus AS marriagestatus,  " +
            "  acd.nativeplace AS nativeplace,  " +
            "  acd.socialsecurity AS socialidentity,  " +
            "  acd.proptimes AS proptimes,  " +
            "  acd.propstatus AS propstatus,  " +
            "  acd.profession AS profession,  " +
            "  acd.age AS age,  " +
            "  acd.familystructure AS familystructure,  " +
            "  CASE WHEN isnull(nc.nuastateid) THEN '未分配' ELSE nc.nuastateid END AS nuastateid,  " +
            "  tsb.realname AS realname,  " +
            "  nc.fpstatetime AS fpstatetime,  " +
            "  bi.buildname AS lvjubuild,  " +
            "  cto.submoney AS lvjumoney,  " +
            "  cto.createtime AS lvjutime, "+
            "  cto.remark AS lvjuremark,  " +
            "  (SELECT count(*) from cust_history_record where phonenumber=nc.phonenumber) AS totalcount,  " +
            "  (SELECT MAX(createtime) from cust_history_record where phonenumber=nc.phonenumber) AS followtime  " +
            "  FROM n_customsallocate nc  " +
            "  LEFT JOIN a_agentinfo agi on agi.id = nc.agentid  " +
            "  LEFT JOIN trade_company_info tra on tra.cmpaccount= agi.subcompany  " +
            "  JOIN a_user aus on aus.id=agi.id  " +
            "  LEFT JOIN a_agentcustomdetail acd on acd.agentid= nc.agentid  " +
            "  LEFT JOIN b_buildbaseinfo bbi on bbi.id=acd.intetionbuild  " +
            "  LEFT JOIN bookscanbuilding boo on boo.agentId=nc.agentid  " +
            "  LEFT JOIN t_s_base_user tsb on  tsb.id = nc.userid  " +
            "  LEFT JOIN cust_order cto on cto.custphonenumber=nc.phonenumber and orderstate='2' "+
            "  LEFT JOIN b_buildbaseinfo bi on bi.id=cto.buildid "+
            "  where nc.phonenumber= 15084569652 "+
            " GROUP BY nc.id";
    //客户相关订单信息
    public static String CUST_ORDER_INFO_SQL="SELECT" +
            "  bhi.buildname AS qybuild, " +
            "  CONCAT(bhi.stagename,'-',bhi.housenumber,'-',bhi.unitnumber,'-',bhi.roomnumber) AS houseresource_info," +
            "  cto.signtime AS qytime," +
            "  bbt.housetypedescribe AS housetype," +
            "  bhi.buidingarea AS buidingarea," +
            "  cto.signtotalmoney as qytotalmoney," +
            "  cto.paytype AS paytype," +
            "  cto.realpaymoney AS realpaymoney," +
            "  cto.custname AS custname,   " +
            "  cto.contactaddress AS contactaddress," +
            "  cto.remark AS qyremark," +
            "  bpm.paypoint AS paypoint," +
            "  bpm.actualpaymoney AS actualpaymoney," +
            "  bpm.paymoney AS paymoney," +
            "  SUM(bpm.addedtax+bpm.orderid+bpm.surcharge) AS tax," +
            "  bpm.paymoneydate AS paymoneydate," +
            "  agi.bankcardnumber AS bankcardnumber," +
            "  agi.branchinformation as branchinformation," +
            "  agi.agentname AS agentname," +
            "  agi.identitycard AS identitycard," +
            "  bpm.id AS payid" +
            "  FROM " +
            "  cust_order cto" +
            "  LEFT JOIN b_housebaseinfo bhi on bhi.id = cto.houseresourceid" +
            "  LEFT JOIN b_buildtype bbt on bbt.id= bhi.housetypeid " +
            "  LEFT JOIN b_paymoney bpm on bpm.orderid = cto.id" +
            "  LEFT JOIN a_agentinfo agi on agi.id = bpm.agentid" +
            "  WHERE " +
            "  custphonenumber = 15084569652 " +
            "  AND orderstate = '7'";
    //销售分组下拉
    public static String SALE_GROUP_SQL="SELECT GROUP_CONCAT(b.salename) as salename,b.departname as departname from (SELECT CONCAT(n.sid,'-',n.salename) as salename, n.departname,n.org_code from (SELECT tsb.id AS sid,  " +
            "  tsb.realname AS salename,  " +
            "  tsd.departname AS departname,  " +
            "  tsd.org_code AS org_code  " +
            "FROM  " +
            "  t_s_base_user tsb  " +
            "JOIN t_s_user_org tuo ON tuo.user_id = tsb.id   " +
            "JOIN t_s_depart tsd ON tsd.id = tuo.org_id  " +
            "JOIN t_s_role_user tru ON tru.userid = tsb.ID " +
            "JOIN t_s_role tsr ON tsr.id = tru.roleid  " +
            "AND tsr.rolename = '展示中心销售' " +
            "ORDER BY tsd.org_code DESC) n  " +
            "GROUP BY sid) b  " +
            "GROUP BY  b.departname";
}
