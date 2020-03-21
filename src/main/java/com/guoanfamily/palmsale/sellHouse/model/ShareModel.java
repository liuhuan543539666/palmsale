package com.guoanfamily.palmsale.sellHouse.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Administrator on 2017/5/25.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ShareModel implements Serializable {
    @Id
    private String id;
    private String sharecommon;
    private String shareall;
    private String trade;
    private String sharesum;
}
