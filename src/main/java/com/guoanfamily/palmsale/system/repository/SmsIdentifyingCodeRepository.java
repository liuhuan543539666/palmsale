package com.guoanfamily.palmsale.system.repository;

import com.guoanfamily.palmsale.system.entity.SmsIdentifyingCode;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by lenovo on 2017/6/1.
 */
public interface SmsIdentifyingCodeRepository extends JpaRepository<SmsIdentifyingCode , String>{

    public SmsIdentifyingCode findByphonenum(String phonenum);
}
