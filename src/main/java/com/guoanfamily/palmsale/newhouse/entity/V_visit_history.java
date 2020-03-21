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
@Table(name ="v_visit_history")
public class V_visit_history {
  @Id
  private String uid;
  private String scantime;//预约带看时间
  private Timestamp visit_date;//带看确认日期
  private String channeltype;//带看渠道
  private String actvisiter;//实际带看人
  private String actcustomname;//实际到访客户姓名
  private String actcustomphone;//实际到访客户登记电话
  private String actvistitcount;//实际到访及陪同人数
  private String remark;//备注信息
  private String update_by;//到访确认人
  private String phonenumber;
}
