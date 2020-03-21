package com.guoanfamily.palmsale.sellHouse.entity;

import com.guoanfamily.palmsale.common.abstractobj.IdEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="bookscanbuilding")
public class Bookscanbuilding extends IdEntity {
  private String create_name;
  private String create_by;
  private String update_name;
  private String update_by;
  private java.sql.Timestamp update_date;
  private String custname;
  private String phonenum;
  private String scancount;
  private java.sql.Timestamp scantime;
  private String uid;
  private String visitstate;
  private java.sql.Timestamp visittime;
  private String custphone;
  private String orgcode;
  private java.sql.Timestamp initiationtime;
  private String money;
  private String coupon_code;
}
