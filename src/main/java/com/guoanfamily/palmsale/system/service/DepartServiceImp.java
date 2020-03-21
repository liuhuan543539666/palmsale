package com.guoanfamily.palmsale.system.service;

import com.guoanfamily.palmsale.system.entity.Depart;
import com.guoanfamily.palmsale.system.repository.DepartRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * Created by lenovo on 2017/5/25.
 */
@Service
public class DepartServiceImp implements DepartService{

    private final DepartRepository departRepository;

    public DepartServiceImp(DepartRepository departRepository) {
        this.departRepository = departRepository;
    }

    @Override
    public void removeDepartAndUser(String id) {

    }

    @Override
    public Depart generateOrgCode(Depart depart) {

        int orgCodeLength = 2; // 默认编码长度

        String  newOrgCode = "";
        if(!StringUtils.hasText(depart.getParentdepartid())) { // 第一级编码
            String maxOrgCode = departRepository.findMaxOrgCode();
            if(StringUtils.hasText(maxOrgCode)) {
                String pOrgCode = maxOrgCode.substring(0, maxOrgCode.length() - orgCodeLength);
                String subOrgCode = maxOrgCode.substring(maxOrgCode.length() - orgCodeLength, maxOrgCode.length());
                newOrgCode = pOrgCode + String.format("%0" + orgCodeLength + "d", Integer.valueOf(subOrgCode) + 1);
            } else {
                newOrgCode = "A" + String.format("%0" + orgCodeLength + "d", 1);
            }
        } else { // 下级编码
            String maxOrgCode = departRepository.findMaxOrgCodeByPid(depart.getParentdepartid());
            if(StringUtils.hasText(maxOrgCode)) { // 当前基本有编码时
                String pOrgCode = maxOrgCode.substring(0, maxOrgCode.length() - orgCodeLength);
                String subOrgCode = maxOrgCode.substring(maxOrgCode.length() - orgCodeLength, maxOrgCode.length());
                newOrgCode = pOrgCode + String.format("%0" + orgCodeLength + "d", Integer.valueOf(subOrgCode) + 1);
            } else { // 当前级别没有编码时
                Depart parentDepart = departRepository.findOne(depart.getParentdepartid());
                String curOrgCode = parentDepart.getOrg_code();
                newOrgCode = curOrgCode + "A" + String.format("%0" + orgCodeLength + "d", 1);
            }
        }
        if(StringUtils.hasText(newOrgCode)){
            depart.setOrg_code(newOrgCode);
        }
        return depart;
    }
}
