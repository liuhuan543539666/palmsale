package com.guoanfamily.palmsale.sellHouse.entity;

import com.guoanfamily.palmsale.common.abstractobj.IdEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="a_agentinfo")
public class AgentInfo extends IdEntity {

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


}
