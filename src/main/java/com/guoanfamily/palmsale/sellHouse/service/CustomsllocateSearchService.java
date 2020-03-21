package com.guoanfamily.palmsale.sellHouse.service;

import com.guoanfamily.palmsale.sellHouse.entity.Buildbaseinfo;
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
public class CustomsllocateSearchService {
    /**
     * 首访客户条件查询时动态组装条件
     */
    public Specification<Customsllocate> where(
              String custname, String sex, String phonenumber, String channeltype, String intetionbuildid){
        return new Specification<Customsllocate>() {
            @Override
            public Predicate toPredicate(Root<Customsllocate> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<Predicate>();
                //首访客户名字
                if(custname!=null&&!custname.equals("") && !custname.equals("undefined")){
                    predicates.add(cb.like(root.get("custname"), "%"+ custname + "%"));
                }
                //性别
                if(sex!=null&&!sex.equals("") && !sex.equals("undefined")){
                    predicates.add(cb.equal(root.get("sex"), sex));
                }
                //置业顾问
                /*if(userId!=null&&!userId.equals("") && !userId.equals("undefined")){
                    predicates.add(cb.equal(root.get("agentinfo").get("id"), userId));
                }*/
                //电话号码
                if(phonenumber!=null&&!phonenumber.equals("") && !phonenumber.equals("undefined")){
                    predicates.add(cb.like(root.get("phonenumber"), "%"+ phonenumber + "%"));
                }
                //来源渠道
                if(channeltype!=null&&!channeltype.equals("") && !channeltype.equals("undefined")){
                    predicates.add(cb.equal(root.get("channeltype"), channeltype));
                }
                //意向楼盘
                if(intetionbuildid!=null&&!intetionbuildid.equals("") && !intetionbuildid.equals("undefined")){
                    predicates.add(cb.equal(root.get("buildBaseInfo").get("id"), intetionbuildid));
                }

                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}