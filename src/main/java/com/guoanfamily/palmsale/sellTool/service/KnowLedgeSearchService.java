package com.guoanfamily.palmsale.sellTool.service;

import com.guoanfamily.palmsale.sellTool.entity.KnowLedge;
import org.springframework.stereotype.Service;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.RequestParam;

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
 * Created by Administrator on 2017/5/23.
 */
@Service
public class KnowLedgeSearchService {

    public Specification<KnowLedge> where(
            String classifyid, String title, Integer startbrowsetimes, Integer endbrowsetimes, String startTime, String endTime, String offstartTime, String offendTime){
        return new Specification<KnowLedge>() {
            @Override
            public Predicate toPredicate(Root<KnowLedge> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<Predicate>();
                Date sTime = null;
                Date eTime = null;
                Date osTime = null;
                Date oeTime = null;
                //知识分类
                if(classifyid!=null && !classifyid.equals("")){
                    predicates.add(cb.like(root.get("classifyid"), "%"+ classifyid + "%"));
                }
                //知识题目(模糊匹配)
                if(title != null && !title.equals("")){
                    predicates.add(cb.like(root.get("title"), "%"+title+"%"));
                }
                //浏览次数
                if(startbrowsetimes != null && startbrowsetimes >= 0 && endbrowsetimes != null && endbrowsetimes >= 0){
                    predicates.add(cb.between(root.<Integer>get("browsetimes"), startbrowsetimes, endbrowsetimes));
                } else if(startTime != null && !startTime.equals("")){
                    predicates.add(cb.greaterThanOrEqualTo(root.<Integer>get("browsetimes"), startbrowsetimes));
                } else if (endTime != null && !endTime.equals("")){
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
    public static Date conversionDate(String time){
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
