package com.guoanfamily.palmsale.sellHouse.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
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
@Data
public class BrowseModel implements Serializable {
    @Id
    private String id;
    private String consultant;
    private String other;
    private String sum;
}
