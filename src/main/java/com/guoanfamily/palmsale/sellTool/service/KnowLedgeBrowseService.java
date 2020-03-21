package com.guoanfamily.palmsale.sellTool.service;

import com.guoanfamily.palmsale.sellTool.entity.KnowLedgeBrowse;
import org.springframework.stereotype.Service;
import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/23.
 */
@Service
public class KnowLedgeBrowseService {

    public Specification<KnowLedgeBrowse> where(String id){
        return new Specification<KnowLedgeBrowse>() {
            @Override
            public Predicate toPredicate(Root<KnowLedgeBrowse> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<Predicate>();
                //浏览查询明细
                if(id!=null && !id.equals("")){
                    predicates.add(cb.equal(root.get("titleid"),  id ));
                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}
