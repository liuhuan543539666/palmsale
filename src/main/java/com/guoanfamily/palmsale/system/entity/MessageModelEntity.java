package com.guoanfamily.palmsale.system.entity;

import com.guoanfamily.palmsale.common.abstractobj.IdEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="message_model")
public class MessageModelEntity{
  @Id
  @GeneratedValue(generator = "paymentableGenerator")
  @GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
  @Column(name = "id", nullable = false, length = 36)
  private String id;
  @Column(name = "model_msg_name")
  private String name;
  @Column(name = "model_msg_info")
  private String info;
  @Column(name = "model_msg_flag")
  private String flag;
  @Column(name = "model_msg_type")
  private String type;
  @Column(name = "model_msg_memo")
  private String memo;
}
