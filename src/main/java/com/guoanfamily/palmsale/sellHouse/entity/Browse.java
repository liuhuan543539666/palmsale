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
@Table(name="p_browse")
public class Browse extends IdEntity {
  //private String id;
  private String userid;
  private String username;
  private java.sql.Timestamp  createtime;
  private String browsetype;
  private String businessid;


}
