package com.guoanfamily.palmsale.xx.entity;

import com.guoanfamily.palmsale.annotation.Excel;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;
import com.guoanfamily.palmsale.common.abstractobj.IdEntity;



/**
 * trade_company表操作
 *
 * @author 张文旭
 * @version 1.0
 * @date 2017-06-21 09:11:19
 */
@Data
@Entity
@Table(name="trade_company")
public class TradeCompanyEntity extends IdEntity implements Serializable {
	private static final long serialVersionUID = 1L;

            
   /**
    *创建人名称
    */
    @Excel(name = "创建人名称", ref = "createName")
    private String createName;
        
   /**
    *创建人登录名称
    */
    @Excel(name = "创建人登录名称", ref = "createBy")
    private String createBy;
        
   /**
    *创建日期
    */
    @Excel(name = "创建日期", ref = "createDate")
    private Date createDate;
        
   /**
    *更新人名称
    */
    @Excel(name = "更新人名称", ref = "updateName")
    private String updateName;
        
   /**
    *更新人登录名称
    */
    @Excel(name = "更新人登录名称", ref = "updateBy")
    private String updateBy;
        
   /**
    *更新日期
    */
    @Excel(name = "更新日期", ref = "updateDate")
    private Date updateDate;
        
   /**
    *所属部门
    */
    @Excel(name = "所属部门", ref = "sysOrgCode")
    private String sysOrgCode;
        
   /**
    *所属公司
    */
    @Excel(name = "所属公司", ref = "sysCompanyCode")
    private String sysCompanyCode;
        
   /**
    *流程状态
    */
    @Excel(name = "流程状态", ref = "bpmStatus")
    private String bpmStatus;
        
   /**
    *公司详情id
    */
    @Excel(name = "公司详情id", ref = "cmpid")
    private String cmpid;
        
   /**
    *公司账号
    */
    @Excel(name = "公司账号", ref = "cmpaccount")
    private String cmpaccount;
        
   /**
    *公司密码
    */
    @Excel(name = "公司密码", ref = "cmppwd")
    private String cmppwd;
    }
