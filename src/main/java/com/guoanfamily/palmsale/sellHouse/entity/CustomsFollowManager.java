package com.guoanfamily.palmsale.sellHouse.entity;

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
@Table(name="cust_history_record")
public class CustomsFollowManager extends IdEntity {
  private String id;
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
  private String intetionbuildid;
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
  private String agentid;
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
  private String customid;
  }
