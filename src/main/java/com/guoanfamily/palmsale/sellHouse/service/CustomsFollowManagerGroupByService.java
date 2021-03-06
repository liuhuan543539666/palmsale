package com.guoanfamily.palmsale.sellHouse.service;

import com.guoanfamily.palmsale.sellHouse.entity.CustomsHistoryGroup;
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
public class CustomsFollowManagerGroupByService {
    /**
     * 跟进客户条件查询时动态组装条件
     */
    public Specification<CustomsHistoryGroup> where(
           String custname,String startDate,String endDate,String agentid,String intentionlevel,String channeltype,String followType,String mincount,String maxcount) {
        return new Specification<CustomsHistoryGroup>() {
            @Override
            public Predicate toPredicate(Root<CustomsHistoryGroup> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<Predicate>();
                Date sDate = null;
                Date eDate = null;
                String level = "";
                //首访客户名字
                if (custname != null && !custname.equals("") && !custname.equals("undefined")) {
                    predicates.add(cb.like(root.get("custname"), "%" + custname + "%"));
                }
                //所属顾问
                if (agentid != null && !agentid.equals("") && !agentid.equals("undefined")) {
                    predicates.add(cb.like(root.get("agentname"), "%" + agentid + "%"));
                }
                //意向级别
                if (intentionlevel != null && !intentionlevel.equals("") && !intentionlevel.equals("undefined")) {
                    if(intentionlevel.contains(" ")){
                        level = intentionlevel.trim() + "+";
                        predicates.add(cb.equal(root.get("intentionlevel"),  level));
                    } else{
                        predicates.add(cb.equal(root.get("intentionlevel"),  intentionlevel));
                    }
                }
                //来源渠道
                if (channeltype != null && !channeltype.equals("") && !channeltype.equals("undefined")) {
                    predicates.add(cb.equal(root.get("channeltype"), channeltype));
                }
                //跟进方式
                if (followType != null && !followType.equals("") && !followType.equals("undefined")) {
                    predicates.add(cb.equal(root.get("custevent"), followType));
                }
                //处理日期
                if (startDate != null && !startDate.equals("")) {
                    try {
                        sDate = new SimpleDateFormat("EEE MMM d yyyy HH:mm:ss Z", Locale.ENGLISH).parse(startDate);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                if (endDate != null && !endDate.equals("")) {
                    try {
                        eDate = new SimpleDateFormat("EEE MMM d yyyy HH:mm:ss Z", Locale.ENGLISH).parse(endDate);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                //比较首访时间
                if (startDate != null && !startDate.equals("") && endDate != null && !endDate.equals("")){
                    predicates.add(cb.between(root.<Date>get("createtime"), sDate, eDate));
                } else if(startDate != null && !startDate.equals("")){
                    predicates.add(cb.greaterThanOrEqualTo(root.<Date>get("createtime"), sDate));
                } else if (endDate != null && !endDate.equals("")){
                    predicates.add(cb.lessThanOrEqualTo(root.<Date>get("createtime"), eDate));
                }
                //比较跟进次数
                if (mincount != null && !mincount.equals("") && maxcount != null && !maxcount.equals("")){
                    predicates.add(cb.between(root.<Integer>get("count"), Integer.parseInt(mincount), Integer.parseInt(maxcount)));
                } else if(mincount != null && !mincount.equals("")){
                    predicates.add(cb.greaterThanOrEqualTo(root.<Integer>get("count"), Integer.parseInt(mincount)));
                } else if (maxcount != null && !maxcount.equals("")){
                    predicates.add(cb.lessThanOrEqualTo(root.<Integer>get("count"), Integer.parseInt(maxcount)));
                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}