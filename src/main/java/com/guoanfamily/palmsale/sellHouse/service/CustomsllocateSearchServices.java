package com.guoanfamily.palmsale.sellHouse.service;

import com.guoanfamily.palmsale.sellHouse.entity.AgentInfo;
import com.guoanfamily.palmsale.sellHouse.entity.CustomSalloCate;
import com.guoanfamily.palmsale.sellHouse.entity.CustomSalloCate;
import com.guoanfamily.palmsale.sellTool.service.KnowLedgeSearchService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * @author Petri Kainulainen
 */
@Service
public class CustomsllocateSearchServices {
    /**
     *  条件查询时动态组装条件
     * @param custname
     * @param phonenumber
     * @return Specification<Customsllocate>
     */
    public Specification<CustomSalloCate> where(
             String custname, String phonenumber, String agentid,String startTime,String endTime,Integer dealstatus){
        return new Specification<CustomSalloCate>() {
            @Override
            public Predicate toPredicate(Root<CustomSalloCate> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<Predicate>();
                Date sTime = null;
                Date eTime = null;
                //首访客户名字
                if(custname!=null && !custname.equals("")){
                    predicates.add(cb.like(root.get("custname"), "%"+ custname + "%"));
                }
                //成交状态
                if(dealstatus !=null && dealstatus >= 0){
                    predicates.add(cb.equal(root.get("dealstatus"), dealstatus));
                }
                //所属置业顾问
                if(agentid != null && !agentid.equals("")){
                    predicates.add(cb.equal(root.get("id").as(AgentInfo.class), agentid));
                }
                //电话号码
                if(phonenumber!=null && !phonenumber.equals("")){
                    predicates.add(cb.like(root.get("phonenumber"), "%"+ phonenumber + "%"));
                }
                //处理日期
                sTime = KnowLedgeSearchService.conversionDate(startTime);
                eTime = KnowLedgeSearchService.conversionDate(endTime);
                //比较入会时间
                if (startTime != null && !startTime.equals("") && endTime != null && !endTime.equals("")){
                    predicates.add(cb.between(root.<Date>get("signtime"), sTime, eTime));
                } else if(startTime != null && !startTime.equals("")){
                    predicates.add(cb.greaterThanOrEqualTo(root.<Date>get("signtime"), sTime));
                } else if (endTime != null && !endTime.equals("")){
                    predicates.add(cb.lessThanOrEqualTo(root.<Date>get("signtime"), eTime));
                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}