package com.guoanfamily.palmsale.sellHouse.service;

import com.guoanfamily.palmsale.sellHouse.entity.CustOrder;
import com.guoanfamily.palmsale.sellHouse.entity.Initiationinfo;
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
public class CustomsOrderService {
    /**
     * 入会客户条件查询时动态组装条件
     */
    public Specification<CustOrder> where(
           String custname, String idcard, String phonenumber, String agentid, String mincount, String maxcount, String status, String code) {
        return new Specification<CustOrder>() {
            @Override
            public Predicate toPredicate(Root<CustOrder> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<Predicate>();
                //客户名称
                if (custname != null && !custname.equals("") && !custname.equals("undefined")) {
                    predicates.add(cb.like(root.get("custname"), "%" + custname + "%"));
                }
                //手机号码
                if (phonenumber != null && !phonenumber.equals("") && !phonenumber.equals("undefined")) {
                    predicates.add(cb.like(root.get("custphone"), "%" + phonenumber + "%"));
                }

                //身份证号
                if (idcard != null && !idcard.equals("") && !idcard.equals("undefined")) {
                    predicates.add(cb.like(root.get("idcard"), "%" + idcard + "%"));
                }
                //所属顾问
                if (agentid != null && !agentid.equals("") && !agentid.equals("undefined")) {
                    predicates.add(cb.like(root.get("agentid"), "%" + agentid + "%"));
                }
                //是否入会
                if (status != null && !status.equals("") && !status.equals("undefined")) {
                    predicates.add(cb.equal(root.get("wxmsg"), status));
                }
                //券码
                if (code != null && !code.equals("") && !code.equals("undefined")) {
                    predicates.add(cb.equal(root.get("code"), code));
                }

                //比较跟进次数
                if (mincount != null && !mincount.equals("") && maxcount != null && !maxcount.equals("")){
                    predicates.add(cb.between(root.<Integer>get("submoney"), Integer.parseInt(mincount), Integer.parseInt(maxcount)));
                } else if(mincount != null && !mincount.equals("")){
                    predicates.add(cb.greaterThanOrEqualTo(root.<Integer>get("submoney"), Integer.parseInt(mincount)));
                } else if (maxcount != null && !maxcount.equals("")){
                    predicates.add(cb.lessThanOrEqualTo(root.<Integer>get("submoney"), Integer.parseInt(maxcount)));
                }

                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}