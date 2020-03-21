package com.guoanfamily.palmsale.newhouse.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Administrator on 2017/5/25.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SaleCountsModel {
    @Id
    private String saleid;
    private String salename;
    private String num;
    private String years;
}
