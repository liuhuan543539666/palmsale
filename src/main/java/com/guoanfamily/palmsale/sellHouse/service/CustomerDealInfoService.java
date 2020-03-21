package com.guoanfamily.palmsale.sellHouse.service;

import com.guoanfamily.palmsale.sellHouse.entity.CustomerDealInfo;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/2.
 */
@Service
public class CustomerDealInfoService {

    public Specification<CustomerDealInfo> where(String id){
        return new Specification<CustomerDealInfo>() {
            @Override
            public Predicate toPredicate(Root<CustomerDealInfo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<Predicate>();
                //
                if(id!=null && !id.equals("")){
                    predicates.add(cb.equal(root.get("id"),  id ));
                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}
