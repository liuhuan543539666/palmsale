package com.guoanfamily.palmsale.system.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.guoanfamily.palmsale.common.abstractobj.IdEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by lenovo on 2017/5/16.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="t_s_base_user")
public class User extends IdEntity {
    private Long activitisync;
    private String browser;
    private String password;
    private String realname;
    private String signature;
    private Long status;
    private String userkey;
    private String username;
    private String departid;
    @Transient
    private Long smsCode;

    @ManyToMany( cascade = {CascadeType.ALL}  )
    @JoinTable(name =  "p_s_role_user",joinColumns = { @JoinColumn(name = "userid") }, inverseJoinColumns = {
            @JoinColumn(name = "roleid") })
    private Set<Role> roles;

    @JsonIgnore
    @ManyToMany( cascade = {CascadeType.REFRESH, CascadeType.MERGE} )
    @JoinTable(name =  "t_s_user_org",joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
            @JoinColumn(name = "org_id") })
    private Set<Depart> departs;

    public void addRole(Role role){
        this.roles.add(role);
    }

    public void removeRole(Role role){
        if (this.roles.contains(role)){
            this.roles.remove(role);
        }
    }
}
