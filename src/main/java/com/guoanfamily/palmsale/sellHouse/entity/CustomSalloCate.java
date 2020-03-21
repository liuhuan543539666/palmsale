package com.guoanfamily.palmsale.sellHouse.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.guoanfamily.palmsale.common.abstractobj.IdEntity;
import lombok.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "n_customsallocate")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CustomSalloCate extends IdEntity{

  private String custname;
  private String sex;
  private String phonenumber;
  private String age;
  private java.sql.Timestamp birthday;
  private String email;
  private String channeltype;
  private String userid;
  private String custdiscription;
  private String custeventid;
  private String eventdiscription;
  private java.sql.Timestamp createtime;
  private String nuastateid;
  private String inthouserestype;
  private String importinfo;
  private String inthousetype;
  private String workarea;
  private String livearea;
  private String familystructure;
  private String prjthobby;
  private String prjtres;
  private String propaim;
  private String paytype;
  private String profession;
  private String tripmode;
  private String workunitnature;
  private String famanincome;
  private String intentionlevel;
  private java.sql.Timestamp fpstatetime;
  private String fpflag;
  private java.sql.Timestamp recommendedtime;
  private String intentionprice;
  private String allocateid;
  private String purchasepower;
  private String matehobby;
  private String vehiclesituation;
  private String cusjob;
  private String proptype;
  private String cushobby;
  private String childname;
  private String matephonenumber;
  private String childbrandchoose;
  private String currentaddress;
  private String familymembers;
  private String accchildsex;
  private String isdecisionmarker;
  private String matebrandchoose;
  private String childhobby;
  private String purchaseexperience;
  private String foreignlanguagelevel;
  private String educationlevel;
  private String accename;
  private String propertytype;
  private String marriagestatus;
  private String isaccmarrage;
  private String incomelevel;
  private String accphonenumber;
  private String mateage;
  private String ischildmarrage;
  private String collectproperty;
  private String childsex;
  private String isoverseasstudy;
  private String graduationschool;
  private String othersituations;
  private String childphonenumber;
  private String proopinion;
  private String accjob;
  private String politicalaffiliation;
  private String acchobby;
  private String major;
  private String childjob;
  private String speciality;
  private String accbrandchoose;
  private String socialidentity;
  private String companyaddress;
  private String companyname;
  private String accage;
  private String childage;
  private String relationship;
  private String matesex;
  private String actualuser;
  private String nativeplace;
  private String islonglive;
  private String companysituation;
  private String matejob;
  private String matename;
  private String idcardnumber;
  private Integer dealstatus;   //成交状态

  @ManyToOne(targetEntity = AgentInfo .class)
  @JoinColumn(name = "agentid")
  private AgentInfo agentInfo;

  @ManyToOne(targetEntity = Buildbaseinfo .class)
  @JoinColumn(name = "intetionbuildid")
  @NotFound(action= NotFoundAction.IGNORE)
  private Buildbaseinfo buildBaseInfo;



}
