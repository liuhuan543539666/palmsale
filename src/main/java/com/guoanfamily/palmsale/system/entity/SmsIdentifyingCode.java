package com.guoanfamily.palmsale.system.entity;

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
@Table(name="a_code")
public class SmsIdentifyingCode extends IdEntity{

  private String phonenum;
  private String code;
  private java.sql.Timestamp createtime;
}
