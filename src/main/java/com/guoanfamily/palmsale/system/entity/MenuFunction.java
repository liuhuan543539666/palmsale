package com.guoanfamily.palmsale.system.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.guoanfamily.palmsale.common.abstractobj.IdEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by lenovo on 2017/5/16.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="p_s_function")
public class MenuFunction extends IdEntity {

//  private Long functioniframe;
  private Long level;
  private String label;
  private String functionorder;
  private String url;
  private String parent;
//  private String iconid;
//  private String desk_iconid;
//  private Long functiontype;
//  private String create_by;
//  private String create_name;
//  private String update_by;
//  private java.sql.Timestamp update_date;
//  private java.sql.Timestamp create_date;
//  private String update_name;

  @JsonIgnore
  @ManyToMany(cascade = CascadeType.REFRESH ,mappedBy="menuSet", fetch = FetchType.LAZY )
  private Set<Role> roleSet;
  @Transient
  private List<MenuFunction> children;

  @Transient
  private String value;

  public String getValue() {
    return super.getId();
  }
}
