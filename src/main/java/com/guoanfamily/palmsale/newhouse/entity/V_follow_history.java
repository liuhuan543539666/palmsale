package com.guoanfamily.palmsale.newhouse.entity;
import com.guoanfamily.palmsale.common.abstractobj.IdEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.data.annotation.Persistent;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.sql.Timestamp;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="v_follow_history")
public class V_follow_history {
  @Id
  private String uid;
  private String phonenumber;
  private String followtype;
  private String eventdiscription;
  private Timestamp createtime;
  private String realname;
}
