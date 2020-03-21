package com.guoanfamily.palmsale.sellTool.entity;

import com.guoanfamily.palmsale.common.abstractobj.IdEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "p_knowledge")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class KnowLedge extends IdEntity{
  private String classifyid;   //知识分类
  private String title;   //题目
  private String img;    //图片
  private String content;   //内容
  private Long browsetimes;    //浏览次数
  private java.sql.Timestamp onlinetime;   //上架时间
  private java.sql.Timestamp offlinetime;   //下架时间
  private Long status;       //状态


}
