package com.guoanfamily.palmsale.sellHouse.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.guoanfamily.palmsale.common.abstractobj.IdEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="cust_order")
public class CustOrder extends IdEntity {
  private String custphonenumber;
  private String substateid;
  private String subtotalprice;
  private java.sql.Timestamp subtime;
  private String submoney;
  private String suburlname;
  private String discount;
  private String houseresourceid;
  private String contactaddress;
  private String orderstate;
  private String custphone;
  private String idcard;
  private String custname;
  private String signtotalmoney;
  private String signtype;
  private String firstpay;
  private java.sql.Timestamp signtime;
  private String paytype;
  private java.sql.Timestamp presigntime;
  private String subscriptioncertificate;
  private String iscomplete;
  private String familycode;
  private String remark;
  private java.sql.Timestamp submitaudittime;
  private String capacityauditcertificate;
  private String drawbacktype;
  private String drawbackreason;
  private String drawbackmoney;
  private String drawbackcertificate;
  private String firstpayratio;
  private String contractno;
  private java.sql.Timestamp contractsigndate;
  private String mortgagemoney;
  private String mortgagebank;
  private String nopasstype;
  private String nopassreason;
  private java.sql.Timestamp resultopendate;
  private java.sql.Timestamp applaydrawbackdate;
  private String delaytype;
  private String delayreason;
  private java.sql.Timestamp delayexpirationdate;
  private String delayauditcertificate;
  private String signcertificate;
  private String closedealtype;
  private String closedealreason;
  private java.sql.Timestamp closedealdate;
  private String closedealcertificate;
  private String nopasscertificate;
  private String ordercustname;
  private String ordercustphonenumber;
  private String ordercustidcardno;
  private String orderno;
  private String isdrawback;
  private java.sql.Timestamp drawbackdate;
  private java.sql.Timestamp createtime;
  private java.sql.Timestamp firstpaydate;
  private String realpaymoney;
  private String firtpaydealno;
  private String submoneypayno;
  private String hwtid;
  private String wxmsg;

  @OneToMany(fetch= FetchType.EAGER)
  @JoinColumn(name="orderid")
  private Set<InvitedCustInfo> invitedcustinfo = new HashSet<InvitedCustInfo>();

  @Transient
  private String orderstates;//支付状态字段
  public String getorderstates() {
    String state = null;
    if(this.orderstate != null && this.orderstate.equals("")) {
      if(this.orderstate.equals("2")) {
        state = "1"; //已支付
      }else {
        state = "2"; //未支付
      }
    }else {
      state = "2";
    }
    return state;
  }

  @Transient
  private String countDown; //认购到期倒计时
  public String getcountDown() {
    String countdowntime;
    if(getCreatetime() != null && !getCreatetime().equals("")) {
      long time = getCreatetime().getTime();//获取认购信息提交时间
      SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
      long nowtime = new Date().getTime();// new Date()为获取当前系统时间
      long rightTime = time - nowtime;
      long mt = 86400000L * 45;

      //countdowntime = df.format(rightTime);
      if(rightTime + mt > 0) {
        countdowntime  = formatTime(rightTime + mt);
      } else{
        countdowntime = "0天";
      }
    } else{
      countdowntime = "0天";
    }
    return countdowntime;
  }

  /*
 * 毫秒转化时分秒毫秒
 */
  public static String formatTime(Long ms) {
    Integer ss = 1000;
    Integer mi = ss * 60;
    Integer hh = mi * 60;
    Integer dd = hh * 24;

    Long day = ms / dd;
    Long hour = (ms - day * dd) / hh;
    Long minute = (ms - day * dd - hour * hh) / mi;
    Long second = (ms - day * dd - hour * hh - minute * mi) / ss;
    Long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;

    StringBuffer sb = new StringBuffer();
    if(day > 0) {
      sb.append(day+"天");
    }
    /*if(hour > 0) {
      sb.append(hour+"小时");
    }
    if(minute > 0) {
      sb.append(minute+"分");
    }
    if(second > 0) {
      sb.append(second+"秒");
    }
    if(milliSecond > 0) {
      sb.append(milliSecond+"毫秒");
    }*/
    return sb.toString();
  }
}
