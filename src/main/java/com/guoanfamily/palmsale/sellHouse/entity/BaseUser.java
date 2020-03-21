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
@Table(name="t_s_base_user")
public class BaseUser extends IdEntity {
  private String id;
  private Long activitisync;
  private String browser;
  private String password;
  private String realname;
  private String signature;
  private Long status;
  private String userkey;
  private String username;
  private String departid;
}
