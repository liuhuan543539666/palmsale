package com.guoanfamily.palmsale.newhouse.entity;

import com.guoanfamily.palmsale.common.abstractobj.IdEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.omg.CORBA.portable.IDLEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bookscanbuilding")
public class Bookscanbuilding extends IdEntity{
  private String create_name;
  private String create_by;
  private String update_name;
  private String update_by;
  private Date update_date;
  private String custname;
  private String phonenum;
  private String scancount;
  private String scantime;
  private String bookbuilding;
  private String uid;
  private String visitstate;
  private java.sql.Timestamp visittime;
  private String custphone;
  private String orgcode;
  private String address;
  private String othername;
  private String otherphone;
  private String agentid;
  private String remark;
  private String datatype;
  private String customerdescription;
  private String sex;
  private String intentionfloor;
  private String intentionlevel;
  private String intentionarea;
  private String purchasetype;
  private String intentionroom;
  private String totalpriceregion;
  private String age;
  private String profession;
  private String familystructure;
  private String livearea;
  private String workarea;
  private String propaim;
  private String importinfo;
  private String intetionbuild;
  private String nativeplace;
  private String socialsecurity;
  private String proptimes;
  private String propstatus;
  private String marriagestatus;
  private String cardtype;
  private String idcardnumber;
  private String state;
  private String buildname;
  private String buildid;
  private String propertytype;
  private String actvisiter;
  private String actcustomname;
  private String actcustomphone;
  private String actvistitcount;
  private String channeltype;
  private java.sql.Timestamp visit_date;

}
