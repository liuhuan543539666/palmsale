package com.guoanfamily.palmsale.sellTool.entity;

import com.guoanfamily.palmsale.common.abstractobj.IdEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "p_hotarticleshare")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class HotarticleShare extends IdEntity{

  private String username;
  private Long integral;
  private java.sql.Timestamp createtime;
  private String softtitleid;


}
