package com.guoanfamily.palmsale.sellTool.entity;
import com.guoanfamily.palmsale.common.abstractobj.IdEntity;
import com.guoanfamily.palmsale.sellHouse.entity.CustomerDealInfo;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "p_hotarticle")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Hotarticle extends IdEntity{

  private String title;
  private String content;
  private Integer integral;
  private String img;
  private java.sql.Timestamp onlinetime;
  private java.sql.Timestamp offlinetime;
  private Integer status;

  @OneToMany(cascade= CascadeType.ALL,fetch= FetchType.LAZY)
  @JoinColumn(name="softtitleid")
  private Set<HotarticleShare> hotarticleShares = new HashSet<HotarticleShare>();


}
