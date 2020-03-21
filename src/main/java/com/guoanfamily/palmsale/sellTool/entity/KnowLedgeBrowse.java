package com.guoanfamily.palmsale.sellTool.entity;

import com.guoanfamily.palmsale.common.abstractobj.IdEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "p_knowledgebrowse")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class KnowLedgeBrowse extends IdEntity{
  private String username;
  private java.sql.Timestamp starttime;
  private java.sql.Timestamp endtime;
  private java.sql.Timestamp createtime;
  private java.sql.Timestamp updatetime;
  private String titleid;



}
