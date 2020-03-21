package com.guoanfamily.palmsale.newhouse.entity;

import com.guoanfamily.palmsale.common.abstractobj.IdEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "p_c_count")
public class PCCount extends IdEntity {
  private int p_cust_allot;//待分配客户
  private int p_lvju_order;//待处理入会订单
  private int p_confirm_vistit;//待确认预约带看
  private int p_cust_book;//待处理客户预约
  private int p_cust_delay_reallot;//待处理客户逾期重新分配
  private int p_pay_split_ratio;//待确认佣金拆账比例
  private int p_cust_buy_audit;//待处理客户认购审批
  private int p_lvju_drawback;//待处理入会退款
  private int p_pay_audit;//待处理结佣审批
  private Date create_date;//创建日期
}
