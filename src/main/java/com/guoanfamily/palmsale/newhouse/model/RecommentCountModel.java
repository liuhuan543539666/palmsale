package com.guoanfamily.palmsale.newhouse.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Administrator on 2017/5/24.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RecommentCountModel {
    @Id
    private String days;
    private Integer num;
}
