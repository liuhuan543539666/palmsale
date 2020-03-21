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
@Table(name="n_customsallot_records")
public class CustomsallotRecords extends IdEntity {
  //private String id;
  private String customerid;
  private String countdown;
  private java.sql.Timestamp createtime;
  private String agentname;
  private String reagentname;
  private String reason;
  private String disapprovalreason;
  private String userid;
}
