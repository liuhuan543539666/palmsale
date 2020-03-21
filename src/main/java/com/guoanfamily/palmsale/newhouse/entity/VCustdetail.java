package com.guoanfamily.palmsale.newhouse.entity;

import com.guoanfamily.palmsale.common.abstractobj.IdEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.data.annotation.Persistent;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="v_custdetail")
public class VCustdetail {
  @Id
  private String uid;
  private String custname;
  private String sex;
  private String phonenumber;
  private String channeltype;
  private String cmpname;
  private String agentname;
  private String agentphonenum;
  private String buildname;
  private String custstate;
  private String scantime;
  private String address;
  private String cardtype;
  private String cardid;
  private String custlabel;
  private String othername;
  private String otherphone;
  private String remark;
  private String intentionlevel;
  private String intentionare;
  private String intentionfloor;
  private String importinfo;
  private String propaim;
  private String livearea;
  private String workarea;
  private String marriagestatus;
  private String nativeplace;
  private String socialidentity;
  private String proptimes;
  private String propstatus;
  private String profession;
  private String age;
  private String familystructure;
  private String nuastateid;
  private String realname;
  private Date fpstatetime;
  private Integer totalcount;
  private Date followtime;
  private String lvjubuild;
  private String lvjumoney;
  private Date lvjutime;
  private Date recommendedtime;
  private String lvjuremark;
}
