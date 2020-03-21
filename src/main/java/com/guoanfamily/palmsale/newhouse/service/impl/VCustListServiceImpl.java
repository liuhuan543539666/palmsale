package com.guoanfamily.palmsale.newhouse.service.impl;

import com.github.wenhao.jpa.Sorts;
import com.github.wenhao.jpa.Specifications;
import com.guoanfamily.palmsale.newhouse.entity.A_agentcustomdetail;
import com.guoanfamily.palmsale.newhouse.entity.NCustomsallocate;
import com.guoanfamily.palmsale.newhouse.entity.VCustList;
import com.guoanfamily.palmsale.newhouse.entity.VCustdetail;
import com.guoanfamily.palmsale.newhouse.repository.AAgentCustomdetailRepository;
import com.guoanfamily.palmsale.newhouse.repository.NCustomsallocateRepository;
import com.guoanfamily.palmsale.newhouse.repository.VCustListRepository;
import com.guoanfamily.palmsale.newhouse.service.ObjectDaoServiceI;
import com.guoanfamily.palmsale.newhouse.service.VCustListServiceI;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Range;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/5/17.
 */
@Transactional
@Service
public class VCustListServiceImpl implements VCustListServiceI {
    @Autowired
    private VCustListRepository vCustListRepository;
    @Autowired
    private NCustomsallocateRepository nCustomsallocateRepository;
    @Autowired
    private AAgentCustomdetailRepository aAgentCustomdetailRepository;
    public Page<VCustList> findAll(VCustList req){
        Specification<VCustList> specification = null;
        Sort sort=null;
        String [] times= new String[2];
        try {
            //时间处理
            String creatime = "";
            if(req.getBetweentime()!=null&&req.getBetweentime()!=""){
                creatime  =req.getBetweentime().toString();
                times = creatime.split(",");
            }
           specification = Specifications.<VCustList>and()
                    .like(StringUtils.isNotBlank(req.getCustname()),"custname","%"+req.getCustname()+"%")
                    .like(StringUtils.isNotBlank(req.getPhonenumber()),"phonenumber","%"+req.getPhonenumber()+"%")
                    .like(StringUtils.isNotBlank(req.getNuastateid()),"nuastateid","%"+req.getNuastateid()+"%")
                    .like(StringUtils.isNotBlank(req.getChanneltype()),"channeltype","%"+req.getChanneltype()+"%")
                    .like(StringUtils.isNotBlank(req.getCmpname()),"cmpname","%"+req.getCmpname()+"%")
                    .like(StringUtils.isNotBlank(req.getBuildname()),"buildname","%"+req.getBuildname()+"%")
                    .like(StringUtils.isNotBlank(req.getRealname()),"realname","%"+req.getRealname()+"%")
                    .between(req.getBetweentime()!=null&&req.getBetweentime()!="","createdate",new Range<Date>(getDate(times[0]), getDate(times[1]))).build();
           sort = Sorts.builder().desc("createdate").build();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return vCustListRepository.findAll(specification,new PageRequest(Integer.parseInt(req.getPage()),10,sort));
    }

    @Override
    public boolean editEntity(VCustdetail req, NCustomsallocate model, A_agentcustomdetail cdetail) {
        model.setCustname(req.getCustname());
        model.setSex(req.getSex());
        model.setChanneltype(req.getChanneltype());
        model.setCuststate(req.getCuststate());
        model.setCardtype(req.getCardtype());
        model.setIdcardnumber(req.getCardid());
        model.setRemark(req.getRemark());
        model.setIntentionlevel(req.getIntentionlevel());
        model.setIntentionare(req.getIntentionare());
        model.setIntentionfloor(req.getIntentionfloor());
        model.setLivearea(req.getLivearea());
        model.setWorkarea(req.getWorkarea());
        model.setMarriagestatus(req.getMarriagestatus());
        model.setNativeplace(req.getNativeplace());
        model.setSocialsecurity(req.getSocialidentity());
        model.setProptimes(req.getProptimes());
        model.setPropertytype(req.getPropstatus());
        model.setProfession(req.getProfession());
        model.setAge(req.getAge());
        model.setFamilystructure(req.getFamilystructure());
        //model.setAccphonenumber(req.getOtherphone());
        model.setAccename(req.getOthername());
        nCustomsallocateRepository.saveAndFlush(model);

        cdetail.setCustname(req.getCustname());
        cdetail.setCardtype(req.getCardtype());
        cdetail.setCardid(req.getCardid());
        cdetail.setRemark(req.getRemark());
        cdetail.setIntentionlevel(req.getIntentionlevel());
        cdetail.setIntentionarea(req.getIntentionare());
        cdetail.setIntentionfloor(req.getIntentionfloor());
        cdetail.setLivearea(req.getLivearea());
        cdetail.setWorkarea(req.getWorkarea());
        cdetail.setMarriagestatus(req.getMarriagestatus());
        cdetail.setNativeplace(req.getNativeplace());
        cdetail.setSocialsecurity(req.getSocialidentity());
        cdetail.setProptimes(req.getProptimes());
        cdetail.setPropstatus(req.getPropstatus());
        cdetail.setProfession(req.getProfession());
        cdetail.setAge(req.getAge());
        cdetail.setFamilystructure(req.getFamilystructure());
        cdetail.setOthername(req.getOthername());
        //cdetail.setOtherphone(req.getOtherphone());
        aAgentCustomdetailRepository.saveAndFlush(cdetail);
        return false;
    }

    //时间格式化方法
    private Date getDate(String source) throws ParseException{
        if(source!=null){
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(source);
        }
        return null;
    }
}
