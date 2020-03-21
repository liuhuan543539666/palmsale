package com.guoanfamily.palmsale.sellHouse.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.guoanfamily.palmsale.common.abstractobj.IdEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="b_buildbaseinfo_detail")
public class BuildbaseinfoDetail extends IdEntity {
  private String pid;
  private String phone;
  private String ad;
  private String housetype;
  private String presale_permit;
  private String onsalestatus;
  private String deadlinestatus;
  private String updown;
  private Date updowntime;
}
