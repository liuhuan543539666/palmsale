package com.guoanfamily.palmsale.sellHouse.service;

import com.guoanfamily.palmsale.sellHouse.entity.AgentInfo;
import com.guoanfamily.palmsale.sellHouse.entity.PayMoneyRecord;
import com.guoanfamily.palmsale.sellTool.entity.HotarticleShare;
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
public class PayMoneyRecordService {

    public Specification<PayMoneyRecord> where(String dealpaymoneyid){
        return new Specification<PayMoneyRecord>() {
            @Override
            public Predicate toPredicate(Root<PayMoneyRecord> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<Predicate>();
                //佣金记录查询  (成交id)
                if((dealpaymoneyid!=null && !dealpaymoneyid.equals(""))){
                    predicates.add(cb.equal(root.get("dealpaymoneyid"),  dealpaymoneyid ));
                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }

}
