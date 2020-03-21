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
@Table(name ="v_cust_list")
public class VCustList{
  @Id
  private String uid;
  private String custname;//客户姓名
  private String phonenumber;//客户手机
  private String channeltype;//认知渠道
  private String buildname;//楼盘名称
  private String nuastateid;//分配状态
  private Timestamp createdate;//推荐时间
  private String realname;//销售姓名
  private String auditstate;//审核状态
  private String cmpname;//渠道公司
  //  @Transient
//  private String rownum;//序列号
  @Transient
  private String betweentime;//时间范围
  @Transient
  private String page;//页码
}
