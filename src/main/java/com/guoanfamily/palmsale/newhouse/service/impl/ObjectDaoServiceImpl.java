package com.guoanfamily.palmsale.newhouse.service.impl;

import com.guoanfamily.palmsale.newhouse.service.ObjectDaoServiceI;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Administrator on 2017/5/17.
 */
@Service
public class ObjectDaoServiceImpl implements ObjectDaoServiceI{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public EntityManager getEntityManager() {
        return this.entityManager;
    }
}
