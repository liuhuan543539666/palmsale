package com.guoanfamily.palmsale.system.repository;

import com.guoanfamily.palmsale.system.entity.MessageModelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by lenovo on 2017/5/31.
 */
public interface MessageRepository extends JpaRepository<MessageModelEntity , String>{

    public MessageModelEntity findByName(String modelMsgName);
}
