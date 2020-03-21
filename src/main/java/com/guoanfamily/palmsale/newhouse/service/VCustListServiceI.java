package com.guoanfamily.palmsale.newhouse.service;

import com.guoanfamily.palmsale.newhouse.entity.A_agentcustomdetail;
import com.guoanfamily.palmsale.newhouse.entity.NCustomsallocate;
import com.guoanfamily.palmsale.newhouse.entity.VCustList;
import com.guoanfamily.palmsale.newhouse.entity.VCustdetail;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 客户信息相关接口
 * Created by Administrator on 2017/5/17.
 */
public interface VCustListServiceI {
    //客户列表动态查询
    public Page<VCustList> findAll(VCustList vCustList);

    /**
     * 更新客户信息
     * @param req
     * @param nCustomsallocate
     * @param a_agentcustomdetail
     * @return
     */
    public boolean editEntity(VCustdetail req, NCustomsallocate nCustomsallocate, A_agentcustomdetail a_agentcustomdetail);
}
