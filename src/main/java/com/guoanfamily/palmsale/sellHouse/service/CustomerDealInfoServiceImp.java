package com.guoanfamily.palmsale.sellHouse.service;

import com.guoanfamily.palmsale.sellHouse.entity.CustomerDealInfo;
import com.guoanfamily.palmsale.sellHouse.repository.CustomSalloCateRepository;
import com.guoanfamily.palmsale.sellHouse.repository.CustomerDealInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.Transient;

/**
 * Created by Administrator on 2017/6/21.
 */
@Service
public class CustomerDealInfoServiceImp implements CustomerDealInfoServiceInterface{

    private CustomerDealInfoRepository customerDealInfoRepository;

    private CustomSalloCateRepository customSalloCateRepository;

    @Autowired
    public CustomerDealInfoServiceImp(CustomerDealInfoRepository customerDealInfoRepository, CustomSalloCateRepository customSalloCateRepository) {
        this.customerDealInfoRepository = customerDealInfoRepository;
        this.customSalloCateRepository = customSalloCateRepository;
    }

    @Transient
    @Override
    public CustomerDealInfo customerDealInfoSave(CustomerDealInfo customerDealInfo) {
        //先修改状态
        if (customerDealInfo.getCustomSalloCate() != null){
            customSalloCateRepository.save(customerDealInfo.getCustomSalloCate());
        }
        //再保存信息
        if (customerDealInfo != null) {
            customerDealInfoRepository.save(customerDealInfo);
        }
        return customerDealInfo;
    }
}
