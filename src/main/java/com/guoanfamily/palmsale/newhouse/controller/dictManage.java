package com.guoanfamily.palmsale.newhouse.controller;

import com.guoanfamily.palmsale.common.AjaxJson;
import com.guoanfamily.palmsale.common.abstractobj.ApiController;
import com.guoanfamily.palmsale.newhouse.Utils.SqlUtils;
import com.guoanfamily.palmsale.newhouse.model.RecommentCountModel;
import com.guoanfamily.palmsale.newhouse.model.SaleGroupModel;
import com.guoanfamily.palmsale.newhouse.model.VThreeDict;
import com.guoanfamily.palmsale.newhouse.service.ObjectDaoServiceI;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.*;

/**
 * 字典查询
 * Created by Administrator on 2017/5/25.
 */
@RestController
@RequestMapping(ApiController.DICT_URL)
public class dictManage {
    @Resource
    private final ObjectDaoServiceI objectDaoService;
    private final AjaxJson ajaxJson;
    public dictManage(ObjectDaoServiceI objectDaoService, AjaxJson ajaxJson) {
        this.objectDaoService = objectDaoService;
        this.ajaxJson = ajaxJson;
    }

    /**
     * 字典查询
     * @return
     */
    @ApiOperation(value = "字典查询", notes = "字典查询")
    @RequestMapping(value = "/dictlist",method = RequestMethod.GET)
    public AjaxJson dictlist(){
        String sql = "select d.childname from  v_threedict d where d.pname='询问重点'";
        String sql2 = "select d.childname from  v_threedict d where d.pname='购房用途' ";
        try {
            Map<String,Object> map = new HashMap<>();
            EntityManager entityManager =objectDaoService.getEntityManager();
            Query query= entityManager.createNativeQuery(sql);
            Query query2= entityManager.createNativeQuery(sql2);
            List<String> list= query.getResultList();
            List<String> list2= query2.getResultList();
            map.put("询问重点",list);
            map.put("购房用途",list2);
            ajaxJson.setMsg("success").setStatus(200).setData(map);
        } catch (Exception e) {
            e.printStackTrace();
            ajaxJson.setMsg("fail").setStatus(201).setSuccess(false);
        }
        return ajaxJson;
    }
    /**
     * 销售分组下拉
     */
    @ApiOperation(value = "销售分组下拉", notes = "销售分组下拉")
    @RequestMapping(value = "/slaeSelectOptions",method = RequestMethod.GET)
    public AjaxJson slaeSelectOptions(){
        String sql = SqlUtils.SALE_GROUP_SQL;
        try {
            EntityManager entityManager =objectDaoService.getEntityManager();
            Query query= entityManager.createNativeQuery(sql, SaleGroupModel.class);
            List<SaleGroupModel> list= query.getResultList();
            List<Object> options = new ArrayList<>();
            if(list.size()>0){
                for(SaleGroupModel model :list){
                    Map<String,Object> map = new LinkedHashMap<>();
                    map.put("value",model.getDepartname());
                    map.put("label",model.getDepartname());
                    String sales = model.getSalename();
                    List<Object> children = new ArrayList<>();
                    String[] namestr = sales.split(",");
                    for(String names:namestr){
                        String [] name = names.split("-");
                        Map<String,String> child = new HashMap<>();
                        child.put("value",name[0]);
                        child.put("label",name[1]);
                        children.add(child);
                    }
                    map.put("children",children);
                    options.add(map);
                }
            }
            ajaxJson.setMsg("success").setStatus(200).setData(options);
        } catch (Exception e) {
            e.printStackTrace();
            ajaxJson.setMsg("fail").setStatus(201).setSuccess(false);
        }
        return ajaxJson;
    }

}
