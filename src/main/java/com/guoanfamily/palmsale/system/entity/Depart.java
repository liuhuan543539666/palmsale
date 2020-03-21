package com.guoanfamily.palmsale.system.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.guoanfamily.palmsale.common.abstractobj.IdEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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
@Table(name="t_s_depart")
public class Depart extends IdEntity{

  @JsonProperty("label")
  private String departname;
  private String description;
  private String parentdepartid;
  private String org_code;
  private String org_type;
  private String mobile;
  private String fax;
  private String address;

  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "departs", fetch = FetchType.LAZY)
  private Set<User> users;

  @Transient
  private String value;

  public String getValue() {
    return super.getId();
  }

  @Transient
  private List<Depart> children;
}
