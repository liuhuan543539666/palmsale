package com.guoanfamily.palmsale.sellHouse.service;
import com.guoanfamily.palmsale.sellHouse.entity.PayMoneyRecord;
import com.guoanfamily.palmsale.sellTool.service.KnowLedgeSearchService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/6/1.
 */
@Service
public class PayMoneyService {

    /**
     *  条件查询时动态组装条件
     * @param custname
     * @param phonenumber
     * @return Specification<Customsllocate>
     */
    public Specification<PayMoneyRecord> where(
            String custname, String phonenumber, String agentid,String startTime,String endTime,Integer startMoney,Integer endMoney,Integer paystate){
        return new Specification<PayMoneyRecord>() {
            @Override
            public Predicate toPredicate(Root<PayMoneyRecord> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<Predicate>();
//                Date sTime = null;
//                Date eTime = null;
                //首访客户名字
                if(custname!=null && !custname.equals("")){
                    predicates.add(cb.like(root.get("custname"), "%"+ custname + "%"));
                }
                //所属置业顾问
                if(agentid != null && !agentid.equals("")){
                    predicates.add(cb.equal(root.get("agentid"), agentid));
                }
                //电话号码
                if(phonenumber!=null && !phonenumber.equals("")){
                    predicates.add(cb.like(root.get("phonenumber"), "%"+ phonenumber + "%"));
                }
                //发放状态
                if(paystate!=null && paystate >= 0){
                    predicates.add(cb.equal(root.get("paymoneystatus"), paystate ));
                }

//                //佣金发放金额
//                if(startMoney != null && startMoney > 0 && endMoney != null && endMoney > 0){
//                    predicates.add(cb.between(root.<Integer>get("paymoney"), startMoney, endMoney));
//                } else if(startMoney != null && !startMoney.equals("")){
//                    predicates.add(cb.greaterThanOrEqualTo(root.<Integer>get("paymoney"), startMoney));
//                } else if (endMoney != null && !endMoney.equals("")){
//                    predicates.add(cb.lessThanOrEqualTo(root.<Integer>get("paymoney"), endMoney));
//                }
//                //处理日期
//                sTime = KnowLedgeSearchService.conversionDate(startTime);
//                eTime = KnowLedgeSearchService.conversionDate(endTime);
//                //比较发放时间
//                if (startTime != null && !startTime.equals("") && endTime != null && !endTime.equals("")){
//                    predicates.add(cb.between(root.<Date>get("paymoneydate"), sTime, eTime));
//                } else if(startTime != null && !startTime.equals("")){
//                    predicates.add(cb.greaterThanOrEqualTo(root.<Date>get("paymoneydate"), sTime));
//                } else if (endTime != null && !endTime.equals("")){
//                    predicates.add(cb.lessThanOrEqualTo(root.<Date>get("paymoneydate"), eTime));
//                }

                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}
