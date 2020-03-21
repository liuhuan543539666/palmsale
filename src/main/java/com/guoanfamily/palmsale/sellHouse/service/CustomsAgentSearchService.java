package com.guoanfamily.palmsale.sellHouse.service;

import com.guoanfamily.palmsale.sellHouse.entity.Customsllocate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Petri Kainulainen
 */
@Service
public class CustomsAgentSearchService {
    /**
     * 首访客户条件查询时动态组装条件
     */
    public Specification<Customsllocate> where(
            String custname, String phonenumber, String agentid, String signtime, String nuastateid){
        return new Specification<Customsllocate>() {
            @Override
            public Predicate toPredicate(Root<Customsllocate> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<Predicate>();
                //首访客户名字
                if(custname!=null&&!custname.equals("") && !custname.equals("undefined")){
                    predicates.add(cb.like(root.get("custname"), "%"+ custname + "%"));
                }
                //电话号码
                if(phonenumber!=null&&!phonenumber.equals("") && !phonenumber.equals("undefined")){
                    predicates.add(cb.like(root.get("phonenumber"), "%"+ phonenumber + "%"));
                }
                //置业顾问
                if(agentid!=null&&!agentid.equals("") && !agentid.equals("undefined")){
                    predicates.add(cb.equal(root.get("agentid"), agentid));
                }
                //客户分配状态
                if(nuastateid!=null&&!nuastateid.equals("") && !nuastateid.equals("undefined")){
                    predicates.add(cb.equal(root.get("nuastateid"), nuastateid));
                }

                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}