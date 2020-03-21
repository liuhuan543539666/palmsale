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
@Table(name="invited_cust_info")
public class InvitedCustInfo extends IdEntity {
  private String invitedname;
  private String invitedidcard;
  private String invitedphone;
  private String invitedaddress;
  private String orderid;
}