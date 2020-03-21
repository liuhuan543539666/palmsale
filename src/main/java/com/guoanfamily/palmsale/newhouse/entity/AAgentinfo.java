package com.guoanfamily.palmsale.newhouse.entity;

import com.guoanfamily.palmsale.common.abstractobj.IdEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="a_agentinfo")
public class AAgentinfo extends IdEntity {
  private String agentname;
  private String identitycard;
  private String idcardpicture;
  private String bankcardnumber;
  private String branchinformation;
  private String authflag;
  private String province;
  private String district;
  private String city;
  private String openid;
  private String idcardpicturereverse;
  private String village;
  private String detailaddress;
  private String livingaddress;
  private String subcompany;
  private String subindustry;
  private String activedegree;
  private String nickname;
  private String agetntlevel;
  private String headpic;
  private java.sql.Timestamp createdate;
}
