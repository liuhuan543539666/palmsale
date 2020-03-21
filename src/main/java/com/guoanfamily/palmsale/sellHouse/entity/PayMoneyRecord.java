package com.guoanfamily.palmsale.sellHouse.entity;

import com.guoanfamily.palmsale.common.abstractobj.IdEntity;
import lombok.Data;
import lombok.*;
import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "b_paymoneyrecord")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PayMoneyRecord extends IdEntity{

  private Double paythemoney;       //本次发放金额
  private Double sneedpaymoney;    //还需发放金额
  private java.sql.Timestamp paymoneydate;  //佣金发放日期
  private String dealpaymoneyid;     //成交信息id

  public String getPaythemoney() {
    String data = null;
    if(paythemoney == null || paythemoney.equals("")) {
      data = "- -";
    }else {
      data = String.valueOf(paythemoney);
    }
    return data;
  }

  public String getSneedpaymoney() {
    String data = null;
    if(sneedpaymoney == null || sneedpaymoney.equals("")) {
      data = "- -";
    }else {
      data = String.valueOf(sneedpaymoney);
    }
    return data;
  }

  public String getPaymoneydate() {
    String data = null;
    if(paymoneydate == null || paymoneydate.equals("")) {
      data = "- -";
    }else {
      data = String.valueOf(paymoneydate);
    }
    return data;
  }

  public String getDealpaymoneyid() {
    String data = null;
    if(dealpaymoneyid == null || dealpaymoneyid.equals("")) {
      data = "- -";
    }else {
      data = dealpaymoneyid;
    }
    return data;
  }

//  @ManyToOne(targetEntity = CustomerDealInfo.class,cascade=CascadeType.MERGE)
//  @JoinColumn(name="dealpaymoneyid")
//  private CustomerDealInfo customerDealInfo;


}