package com.guoanfamily.palmsale.sellHouse.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.guoanfamily.palmsale.common.abstractobj.IdEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="n_customsallocate")
public class Customsllocate extends IdEntity {
  private String id;
  private String custname;
  public String getCustname() {
    String data = null;
    if(custname == null || custname.equals("")) {
      data = "- -";
    }else {
      data = custname;
    }
    return data;
  }
  private String sex;
  public String getSex() {
    String data = null;
    if(sex == null || sex.equals("")) {
      data = "- -";
    }else {
      data = sex;
    }
    return data;
  }
  private String phonenumber;
  public String getPhonenumber() {
    String data = null;
    if(phonenumber == null || phonenumber.equals("")) {
      data = "- -";
    }else {
      data = phonenumber;
    }
    return data;
  }
  private String age;
  public String getAge() {
    String data = null;
    if(age == null || age.equals("")) {
      data = "- -";
    }else {
      data = age;
    }
    return data;
  }
  private java.sql.Timestamp birthday;
  private String email;
  //private String channeltype;
  private String userid;
  private String custdiscription;
  public String getCustdiscription() {
    String data = null;
    if(custdiscription == null || custdiscription.equals("")) {
      data = "- -";
    }else {
      data = custdiscription;
    }
    return data;
  }
  private String custeventid;
  private String eventdiscription;
  private java.sql.Timestamp createtime;
  private String nuastateid;
  private String inthouserestype;
  private String importinfo;
  private String inthousetype;
  public String getInthousetype() {
    String data = null;
    if(inthousetype == null || inthousetype.equals("")) {
      data = "- -";
    }else {
      data = inthousetype;
    }
    return data;
  }
  private String workarea;
  public String getWorkarea() {
    String data = null;
    if(workarea == null || workarea.equals("")) {
      data = "- -";
    }else {
      data = workarea;
    }
    return data;
  }
  private String livearea;
  public String getLivearea() {
    String data = null;
    if(livearea == null || livearea.equals("")) {
      data = "- -";
    }else {
      data = livearea;
    }
    return data;
  }
  private String familystructure;
  public String getFamilystructure() {
    String data = null;
    if(familystructure == null || familystructure.equals("")) {
      data = "- -";
    }else {
      data = familystructure;
    }
    return data;
  }
  private String prjthobby;
  public String getPrjthobby() {
    String data = null;
    if(prjthobby == null || prjthobby.equals("")) {
      data = "- -";
    }else {
      data = prjthobby;
    }
    return data;
  }
  private String prjtres;
  private String propaim;
  public String getPropaim() {
    String data = null;
    if(propaim == null || propaim.equals("")) {
      data = "- -";
    }else {
      data = propaim;
    }
    return data;
  }
  private String paytype;
  private String profession;
  public String getProfession() {
    String data = null;
    if(profession == null || profession.equals("")) {
      data = "- -";
    }else {
      data = profession;
    }
    return data;
  }
  private String tripmode;
  private String workunitnature;
  private String famanincome;
  //private String intentionlevel;
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
  public String getPurchaseexperience() {
    String data = null;
    if(purchaseexperience == null || purchaseexperience.equals("")) {
      data = "- -";
    }else {
      data = purchaseexperience;
    }
    return data;
  }
  private String foreignlanguagelevel;
  private String educationlevel;
  private String accename;
  public String getAccename() {
    String data = null;
    if(accename == null || accename.equals("")) {
      data = "- -";
    }else {
      data = accename;
    }
    return data;
  }
  private String propertytype;
  private String marriagestatus;
  public String getMarriagestatus() {
    String data = null;
    if(marriagestatus == null || marriagestatus.equals("")) {
      data = "- -";
    }else {
      data = marriagestatus;
    }
    return data;
  }
  private String isaccmarrage;
  private String incomelevel;
  private String accphonenumber;
  public String getAccphonenumber() {
    String data = null;
    if(accphonenumber == null || accphonenumber.equals("")) {
      data = "- -";
    }else {
      data = accphonenumber;
    }
    return data;
  }
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
  public String getNativeplace() {
    String data = null;
    if(nativeplace == null || nativeplace.equals("")) {
      data = "- -";
    }else {
      data = nativeplace;
    }
    return data;
  }
  private String socialsecurity;
  public String getSocialsecurity() {
    String data = null;
    if(socialsecurity == null || socialsecurity.equals("")) {
      data = "- -";
    }else {
      data = socialsecurity;
    }
    return data;
  }
  private String islonglive;
  private String companysituation;
  private String matejob;
  private String matename;
  private String idcardnumber;
  public String getIdcardnumber() {
    String data = null;
    if(idcardnumber == null || idcardnumber.equals("")) {
      data = "- -";
    }else {
      data = idcardnumber;
    }
    return data;
  }
  private String buyseniority;
  public String getBuyseniority() {
    String data = null;
    if(buyseniority == null || buyseniority.equals("")) {
      data = "- -";
    }else {
      data = buyseniority;
    }
    return data;
  }
  private String idtype;
  public String getIdtype() {
    String data = null;
    if(idtype == null || idtype.equals("")) {
      data = "- -";
    }else {
      data = idtype;
    }
    return data;
  }
  private String radioage;
  public String getRadioage() {
    String data = null;
    if(radioage == null || radioage.equals("")) {
      data = "- -";
    }else {
      data = radioage;
    }
    return data;
  }
  private String radiofamily;
  public String getRadiofamily() {
    String data = null;
    if(radiofamily == null || radiofamily.equals("")) {
      data = "- -";
    }else {
      data = radiofamily;
    }
    return data;
  }
  //级联置业顾问
  @ManyToOne(targetEntity = AgentInfo.class,cascade=CascadeType.PERSIST)
  @JoinColumn(name="agentid")
  @NotFound(action= NotFoundAction.IGNORE)
  public AgentInfo agentinfo;

  //级联客户跟进
  @OneToMany(fetch=FetchType.EAGER)
  @JoinColumn(name="customid")
  private Set<CustomsFollowManager> customsFollowManager = new HashSet<CustomsFollowManager>();

  //级联订单
  @OneToOne(fetch=FetchType.EAGER)
  @JoinColumns({
          @JoinColumn(name ="phonenumber", referencedColumnName ="custphonenumber", insertable = false, updatable = false),
          @JoinColumn(name ="intetionbuildid", referencedColumnName ="houseresourceid", insertable =false, updatable = false)})
  private CustOrder custOrder;

  //级联意向项目
  @ManyToOne(targetEntity = Buildbaseinfo .class, fetch=FetchType.LAZY)
  @JoinColumn(name = "intetionbuildid")
  @NotFound(action= NotFoundAction.IGNORE)
  private Buildbaseinfo buildBaseInfo;

  //级联成交
  @OneToMany(fetch=FetchType.EAGER)
  @JoinColumn(name="customerid")
  private Set<CustomerDealInfo> customerdealinfo = new HashSet<CustomerDealInfo>();

  //级联分配记录
  @OneToMany
  @JoinColumn(name="customerid")
  private Set<CustomsallotRecords> customsallotRecords = new HashSet<CustomsallotRecords>();

  //级联字典表意向
  @ManyToOne(targetEntity = CustAnalyzeDict .class, fetch=FetchType.LAZY)
  @JoinColumn(name = "intentionlevel")
  @NotFound(action= NotFoundAction.IGNORE)
  private CustAnalyzeDict custAnalyzeDict;

  //级联字典表来源渠道
  @ManyToOne(targetEntity = CustAnalyzeDict .class, fetch=FetchType.LAZY)
  @JoinColumn(name = "channeltype")
  @NotFound(action= NotFoundAction.IGNORE)
  private CustAnalyzeDict channel;

  @Transient
  private String flag; //用户所处状态

  public String getflag() {
    String state = "";
    if (getcustomsFollowManager().size() != 0 && getcustOrder() == null && getPrjthobby().equals("- -") && getCustomerdealinfo().size() ==0) {
      state = "2";//跟进
    } else if (getcustOrder() == null && !getPrjthobby().equals("- -") && getCustomerdealinfo().size() ==0) {
      state = "3";//补充意向
    } else if (getcustOrder() != null && getCustomerdealinfo().size() ==0) {
      state = "4";//下定入会
    } else if(getCustomerdealinfo().size() !=0) {
      state = "5";
    }else {
      state = "1";//首访
    }
    return state;
  }

  @Transient
  private String followUpState;//客户跟进状态
  public String getFollowUpState() {
    String state = null;
    if(getCustomsallotRecords().size() == 0) {
      state = "1"; //新分配用户
    }else {
      state = "2"; //再分配用户
    }
    return state;
  }

  public CustOrder getcustOrder() {
    return custOrder;
  }

  public Set<CustomsFollowManager> getcustomsFollowManager() {
    return customsFollowManager;
  }
}