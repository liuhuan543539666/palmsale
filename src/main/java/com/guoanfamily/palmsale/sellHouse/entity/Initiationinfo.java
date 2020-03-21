package com.guoanfamily.palmsale.sellHouse.entity;
import com.guoanfamily.palmsale.common.abstractobj.IdEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Initiationinfo")
public class Initiationinfo extends IdEntity {
  private String create_name;
  private java.sql.Timestamp create_date;
  private String stageid;
  private String money;
  private String privilegeinfo;
  private String probationrange;
  private String validitytime;
  private String instructions;
  private String agreement;
  private String initiation_name;
  private String status;

  @ManyToMany( cascade = {CascadeType.REFRESH, CascadeType.ALL} )
  @JoinTable(name =  "r_initiation_buildbase",joinColumns = { @JoinColumn(name = "initiationinfo_id") }, inverseJoinColumns = {
          @JoinColumn(name = "buildbaseinfo_id") })
  private Set<Buildbaseinfo> buildbaseinfo;

  public void addBuildbaseinfo(Buildbaseinfo buildbaseinfo){
    this.buildbaseinfo.add(buildbaseinfo);
  }

  public void removeBuildbaseinfo(Buildbaseinfo buildbaseinfo){
    if (this.buildbaseinfo.contains(buildbaseinfo)){
      this.buildbaseinfo.remove(buildbaseinfo);
    }
  }
}
