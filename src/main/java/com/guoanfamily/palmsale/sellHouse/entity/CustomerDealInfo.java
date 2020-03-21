package com.guoanfamily.palmsale.sellHouse.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.guoanfamily.palmsale.common.abstractobj.IdEntity;
import lombok.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "p_customerdealinfo")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CustomerDealInfo extends IdEntity{

//  private String customerid;   /**客户ID*/
  private String housetypeid;  /**户型ID*/
  private String housenumber;  /**楼号*/
  private String unitnumber;   /**单元号*/
  private String housefloor;   /**楼层*/
  private String roomnumber;   /**房间号*/
  private String towards;      /**朝向*/
  private String totalprices;  /**总价*/
  private String buidingarea;  /**建筑面积*/
  private java.sql.Timestamp dealdate;  /**成交日期*/
  private Double paymoney;           //应发佣金总额
  private Double actualpaymoney;    //已发金额
  private String agentid;
  private Integer paymoneystatus;   //佣金发放状态
  private java.sql.Timestamp createtime;
  private java.sql.Timestamp updatetime;

  public String getHousetypeid() {
    String data = null;
    if(housetypeid == null || housetypeid.equals("")) {
      data = "- -";
    }else {
      data = housetypeid;
    }
    return data;
  }

  public String getHousenumber() {
    String data = null;
    if(housenumber == null || housenumber.equals("")) {
      data = "- -";
    }else {
      data = housenumber;
    }
    return data;
  }

  public String getUnitnumber() {
    String data = null;
    if(unitnumber == null || unitnumber.equals("")) {
      data = "- -";
    }else {
      data = unitnumber;
    }
    return data;
  }

  public String getHousefloor() {
    String data = null;
    if(housefloor == null || housefloor.equals("")) {
      data = "- -";
    }else {
      data = housefloor;
    }
    return data;
  }

  public String getRoomnumber() {
    String data = null;
    if(roomnumber == null || roomnumber.equals("")) {
      data = "- -";
    }else {
      data = roomnumber;
    }
    return data;
  }

  public String getBuidingarea() {
    String data = null;
    if(buidingarea == null || buidingarea.equals("")) {
      data = "- -";
    }else {
      data = buidingarea;
    }
    return data;
  }

  public String getTowards() {
    String data = null;
    if(towards == null || towards.equals("")) {
      data = "- -";
    }else {
      data = towards;
    }
    return data;
  }

  public String getTotalprices() {
    String data = null;
    if(totalprices == null || totalprices.equals("")) {
      data = "- -";
    }else {
      data = totalprices;
    }
    return data;
  }

  public String getDealdate() {
    String data = null;
    if(dealdate == null || dealdate.equals("")) {
      data = "- -";
    }else {
      data = String.valueOf(dealdate);;
    }
    return data;
  }

  public String getPaymoney() {
    String data = null;
    if(paymoney == null || paymoney.equals("")) {
      data = "- -";
    }else {
      data = String.valueOf(paymoney);
    }
    return data;
  }

  public String getActualpaymoney() {
    String data = null;
    if(actualpaymoney == null || actualpaymoney.equals("")) {
      data = "- -";
    }else {
      data = String.valueOf(actualpaymoney);
    }
    return data;
  }

  public String getAgentid() {
    String data = null;
    if(agentid == null || agentid.equals("")) {
      data = "- -";
    }else {
      data = String.valueOf(agentid);
    }
    return data;
  }

  public String getPaymoneystatus() {
    String data = null;
    if(paymoneystatus == null || paymoneystatus.equals("")) {
      data = "- -";
    }else {
      data = String.valueOf(paymoneystatus);
    }
    return data;
  }

  //单向关联
  @ManyToOne(cascade = { CascadeType.MERGE  })
  @JoinColumn(name = "customerid")
  private CustomSalloCate customSalloCate;

  @OneToMany(cascade = { CascadeType.ALL })
  @JoinColumn(name="dealpaymoneyid")
  private Set<PayMoneyRecord> payMoneyRecords = new HashSet<>();


}
