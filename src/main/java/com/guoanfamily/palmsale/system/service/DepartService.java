package com.guoanfamily.palmsale.system.service;

import com.guoanfamily.palmsale.system.entity.Depart;

/**
 * Created by lenovo on 2017/5/16.
 */
public interface DepartService {

    public void removeDepartAndUser(String id);
    public Depart generateOrgCode(Depart depart);

}
