package com.guoanfamily.palmsale.system.entity;

import com.guoanfamily.palmsale.common.abstractobj.IdEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "a_partner")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PartnerInfoEntity extends IdEntity implements Serializable {

  private String company;/**公司名称*/
  private String industry;/**所属行业*/
  private String scale;/**公司规模*/
  private String contacts;/**公司联系人姓名*/
  private String contactnumber;/**联系人方式*/
  private String businesscard;/**名片*/
  private Long status;/**状态*/
  private java.sql.Timestamp createdate;/**创建时间*/
  private java.sql.Timestamp updatedate;/**修改时间*/

}
