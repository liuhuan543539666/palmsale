package com.guoanfamily.palmsale.sellTool.service;

import com.guoanfamily.palmsale.sellTool.entity.HotarticleShare;
import com.guoanfamily.palmsale.sellTool.entity.KnowLedgeBrowse;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/26.
 */
@Service
public class HotarticleShareService {

    public Specification<HotarticleShare> where(String id){
        return new Specification<HotarticleShare>() {
            @Override
            public Predicate toPredicate(Root<HotarticleShare> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<Predicate>();
                //分享次数查询
                if(id!=null && !id.equals("")){
                    predicates.add(cb.equal(root.get("softtitleid"),  id ));
                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }

}
