package com.guoanfamily.palmsale.sellHouse.service;

import com.guoanfamily.palmsale.sellHouse.entity.CustomsFollowManager;
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
public class CustomsFollowUpSearchService {
    /**
     * 客户跟进管理详细列表条件组装
     */
    public Specification<CustomsFollowManager> where(
           String custname,String customid) {
        return new Specification<CustomsFollowManager>() {
            @Override
            public Predicate toPredicate(Root<CustomsFollowManager> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<Predicate>();
                //跟进客户姓名
                if (custname != null && !custname.equals("") && !custname.equals("undefined")) {
                    predicates.add(cb.equal(root.get("custname"), custname));
                }
                //跟进客户userid
                if (customid != null && !customid.equals("") && !customid.equals("undefined")) {
                    predicates.add(cb.equal(root.get("customid"), customid));
                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}