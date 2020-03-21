package com.guoanfamily.palmsale.newhouse.entity;
import com.guoanfamily.palmsale.common.abstractobj.IdEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="a_agentcustomdetail")
public class A_agentcustomdetail extends IdEntity {
  private String agentid;
  private String custname;
  private String custsex;
  private String custphonenumber;
  private String purchasetype;
  private String intentionroom;
  private String totalpriceregion;
  private String age;
  private String profession;
  private String familystructure;
  private String livearea;
  private String workarea;
  private String customerdescription;
  private String intentionarea;
  private String petcustname;
  private Date visittime;
  private String visitcomment;
  private Date incomingtime;
  private String incomingcomment;
  private String intentionlevel;
  private String intentionfloor;
  private String propaim;
  private String importinfo;
  private String intetionbuild;
  private String nativeplace;
  private String socialsecurity;
  private String proptimes;
  private String propstatus;
  private String marriagestatus;
  private String cardtype;
  private String cardid;
  private String remark;
  private String otherphone;
  private String othername;
  private String custlabel;
}
