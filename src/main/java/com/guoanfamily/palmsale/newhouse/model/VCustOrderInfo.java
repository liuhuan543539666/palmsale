package com.guoanfamily.palmsale.newhouse.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by Administrator on 2017/5/25.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class VCustOrderInfo {
  @Id
  private String qybuild;
  private String houseresource_info;
  private Date qytime;
  private String housetype;
  private Double buidingarea;
  private String qytotalmoney;
  private String paytype;
  private String realpaymoney;
  private String custname;
  private String contactaddress;
  private String qyremark;
  private String paypoint;
  private String actualpaymoney;
  private String paymoney;
  private Double tax;
  private Date paymoneydate;
  private String bankcardnumber;
  private String branchinformation;
  private String agentname;
  private String identitycard;
  private String payid;
}
