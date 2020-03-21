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
@Table(name="r_initiation_buildbase")
public class R_Initiation_Buildbase extends IdEntity {
  private String id;
  private String buildbaseinfo_id;
  private String initiationinfo_id;
}
