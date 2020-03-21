package com.guoanfamily.palmsale.sellTool.service;
import com.guoanfamily.palmsale.sellTool.entity.Hotarticle;
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
 * Created by Administrator on 2017/5/25.
 */
@Service
public class HotarticleService {

    public Specification<Hotarticle> where(
            Integer status, String title, Integer startIntegral, Integer endIntegral, Integer startbrowsetimes, Integer endbrowsetimes, String startTime, String endTime, String offstartTime, String offendTime){
        return new Specification<Hotarticle>() {
            @Override
            public Predicate toPredicate(Root<Hotarticle> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<Predicate>();
                Date sTime = null;
                Date eTime = null;
                Date osTime = null;
                Date oeTime = null;
                //软文状态
                if(status != null && status >= 0){
                    predicates.add(cb.equal(root.get("status"), status));
                }
                //知识题目(模糊匹配)
                if(title != null && !title.equals("")){
                    predicates.add(cb.like(root.get("title"), "%"+title+"%"));
                }
                //分享得积分
                if(startIntegral != null && startIntegral > 0 && endIntegral != null && endIntegral > 0){
                    predicates.add(cb.between(root.<Integer>get("integral"), startIntegral, endIntegral));
                } else if(startIntegral != null && !startTime.equals("")){
                    predicates.add(cb.greaterThanOrEqualTo(root.<Integer>get("integral"), startIntegral));
                } else if (endIntegral != null && !endIntegral.equals("")){
                    predicates.add(cb.lessThanOrEqualTo(root.<Integer>get("integral"), endIntegral));
                }
                //分享次数
                if(startbrowsetimes != null && startbrowsetimes > 0 && endbrowsetimes != null && endbrowsetimes > 0){
                    predicates.add(cb.between(root.<Integer>get("browsetimes"), startbrowsetimes, endbrowsetimes));
                } else if(startbrowsetimes != null && !startbrowsetimes.equals("")){
                    predicates.add(cb.greaterThanOrEqualTo(root.<Integer>get("browsetimes"), startbrowsetimes));
                } else if (endbrowsetimes != null && !endbrowsetimes.equals("")){
                    predicates.add(cb.lessThanOrEqualTo(root.<Integer>get("browsetimes"), endbrowsetimes));
                }
                //处理日期
                sTime = conversionDate(startTime);
                eTime = conversionDate(endTime);
                //比较上架时间
                if (startTime != null && !startTime.equals("") && endTime != null && !endTime.equals("")){
                    predicates.add(cb.between(root.<Date>get("onlinetime"), sTime, eTime));
                } else if(startTime != null && !startTime.equals("")){
                    predicates.add(cb.greaterThanOrEqualTo(root.<Date>get("onlinetime"), sTime));
                } else if (endTime != null && !endTime.equals("")){
                    predicates.add(cb.lessThanOrEqualTo(root.<Date>get("onlinetime"), eTime));
                }

                //处理日期
                osTime = conversionDate(offstartTime);
                oeTime = conversionDate(offendTime);
                //比较下架时间
                if (offstartTime != null && !offstartTime.equals("") && offendTime != null && !offendTime.equals("")){
                    predicates.add(cb.between(root.<Date>get("offlinetime"), osTime, oeTime));
                } else if(offstartTime != null && !offstartTime.equals("")){
                    predicates.add(cb.greaterThanOrEqualTo(root.<Date>get("offlinetime"), osTime));
                } else if (offendTime != null && !offendTime.equals("")){
                    predicates.add(cb.lessThanOrEqualTo(root.<Date>get("offlinetime"), oeTime));
                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }


    //处理日期
    public Date conversionDate(String time){
        Date conversionDate = null;
        if (time !=null && !time.equals("")){
            try {
                conversionDate = new SimpleDateFormat("EEE MMM d yyyy HH:mm:ss Z", Locale.ENGLISH).parse(time);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return conversionDate;
    }

}

