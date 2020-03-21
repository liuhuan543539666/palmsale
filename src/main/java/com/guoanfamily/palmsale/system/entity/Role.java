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
@Table(name="p_s_role")
public class Role extends IdEntity {
  private String rolecode;
  private String rolename;
  private String update_name;
  private java.sql.Timestamp update_date;
  private String update_by;
  private String create_name;
  private java.sql.Timestamp create_date;
  private String create_by;

  @JsonIgnore
  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "roles", fetch = FetchType.LAZY)
  private Set<User> users;


  @JoinTable(name =  "p_s_role_function",joinColumns = { @JoinColumn(name = "roleid",referencedColumnName="id") }, inverseJoinColumns = {
          @JoinColumn(name = "functionid",referencedColumnName="id") })
  @ManyToMany( cascade = {CascadeType.REFRESH, CascadeType.MERGE} )
  private Set<MenuFunction> menuSet;

  public void addMenuFunction(MenuFunction menuFunction){
    this.menuSet.add(menuFunction);
  }

  public void removeMenuFuction(MenuFunction menuFunction){
    if (this.menuSet.contains(menuFunction)){
      this.menuSet.remove(menuFunction);
    }
  }

  public String authority() {
    return "ROLE_" + this.rolecode;
  }
}
