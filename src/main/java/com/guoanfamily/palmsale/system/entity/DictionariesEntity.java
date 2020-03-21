package com.guoanfamily.palmsale.system.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.guoanfamily.palmsale.common.abstractobj.IdEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="cust_analyze_dict")
public class DictionariesEntity extends IdEntity{
  private String dictno;

  @JsonProperty("label")
  private String dictname;
  private String pid;

  @Transient
  private List<DictionariesEntity> children;

  @Transient
  private String value;

  public String getValue() {
    return super.getId();
  }
}
