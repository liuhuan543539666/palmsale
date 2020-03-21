package com.guoanfamily.palmsale.sellHouse.service;

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
public class CustomsInitiationService {
    /**
     * 定金入会条件查询时动态组装条件
     */
    public Specification<Initiationinfo> where(
           String initiation_name,String buildname, String mincount, String maxcount, String status, String startDate, String endDate) {
        return new Specification<Initiationinfo>() {
            @Override
            public Predicate toPredicate(Root<Initiationinfo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<Predicate>();
                Date sDate = null;
                Date eDate = null;
                //入会规则名称
                if (initiation_name != null && !initiation_name.equals("") && !initiation_name.equals("undefined")) {
                    predicates.add(cb.like(root.get("initiation_name"), "%" + initiation_name + "%"));
                }
                //项目名称
                if (buildname != null && !buildname.equals("") && !buildname.equals("undefined")) {
                    predicates.add(cb.like(root.get("buildbaseinfo"), "%" + buildname + "%"));
                }

                //来源渠道
                if (status != null && !status.equals("") && !status.equals("undefined")) {
                    predicates.add(cb.equal(root.get("status"), status));
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
                    predicates.add(cb.between(root.<Date>get("validitytime"), sDate, eDate));
                } else if(startDate != null && !startDate.equals("")){
                    predicates.add(cb.greaterThanOrEqualTo(root.<Date>get("validitytime"), sDate));
                } else if (endDate != null && !endDate.equals("")){
                    predicates.add(cb.lessThanOrEqualTo(root.<Date>get("validitytime"), eDate));
                }
                //比较跟进次数
                if (mincount != null && !mincount.equals("") && maxcount != null && !maxcount.equals("")){
                    predicates.add(cb.between(root.<Integer>get("money"), Integer.parseInt(mincount), Integer.parseInt(maxcount)));
                } else if(mincount != null && !mincount.equals("")){
                    predicates.add(cb.greaterThanOrEqualTo(root.<Integer>get("money"), Integer.parseInt(mincount)));
                } else if (maxcount != null && !maxcount.equals("")){
                    predicates.add(cb.lessThanOrEqualTo(root.<Integer>get("money"), Integer.parseInt(maxcount)));
                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}