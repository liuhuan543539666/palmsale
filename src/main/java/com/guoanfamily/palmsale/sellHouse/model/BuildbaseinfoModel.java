package com.guoanfamily.palmsale.sellHouse.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.guoanfamily.palmsale.common.abstractobj.IdEntity;
import com.guoanfamily.palmsale.sellHouse.entity.CustOrder;
import com.guoanfamily.palmsale.sellHouse.entity.Initiationinfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BuildbaseinfoModel  implements Serializable {
  @Id
  private String id;
  private String buildname;
  private String province;
  private String city;
  private String country;
  private String address;
  private String addressabstract;
  private String averageprice;
  private String openquotationtime;
  private String tenementtype;
  private String arearegion;
  private String launchtime;
  private String decoratestate;
  private String developers;
  private String equityyearlimit;
  private String tenementcompany;
  private String buildtype;
  private String carportmatching;
  private String greeningarea;
  private String volumefraction;
  private String stagename;
  private String tenementfee;
  private String livetime;
  private String totalhouseholdnumber;
  private String greenrate;
  private String stallnumber;
  private String collaboraterulemsg;
  private String onlinestate;
  private String addressarea;
  private String builddesigncompany;
  private String parkdesigncompany;
  private String buildexecutioncompany;
  private String hardbackdesigncompany;
  private String noopsychesyscompany;
  private String occupiedarea;
  private String buildingarea;
  private String buildinfo;
  private String foolernumber;
  private String foolerhight;
  private String hospital;
  private String school;
  private String meal;
  private String shopping;
  private String other;
  private String noopsychefurnishing;
  private String threeconstantsys;
  private String landcertificate;
  private String buildingplanlicence;
  private String buildingexecutionlicence;
  private String buildingopenlicence;
  private String commodityhousedeallicence;
  private String bid;
  private String pid;
  private String phone;
  private String ad;
  private String housetype;
  private String presale_permit;
  private String onsalestatus;
  private String deadlinestatus;
  private String sharecount;
  private String browsecount;
  private String updown;
  private String updowntime;
}
