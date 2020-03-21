package com.guoanfamily.palmsale.newhouse.entity;

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
@Table(name="v_cust_audit")
public class VCustAudit extends IdEntity {
  private String id;
  private String auditname;
  private String workflowname;
  private String realname;
  private String cuserid;
  private java.sql.Timestamp submittime;
  private String auditstate;
  private String fid;
  private String classtype;
  private String auditstateno;
}
