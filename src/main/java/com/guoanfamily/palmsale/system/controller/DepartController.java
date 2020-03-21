package com.guoanfamily.palmsale.system.controller;

import com.guoanfamily.palmsale.common.AjaxJson;
import com.guoanfamily.palmsale.common.abstractobj.ApiController;
import com.guoanfamily.palmsale.system.entity.Depart;
import com.guoanfamily.palmsale.system.repository.DepartRepository;
import com.guoanfamily.palmsale.system.service.DepartService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017/4/25.
 */
@RestController
@RequestMapping(ApiController.DEPART_URL)
public class DepartController extends ApiController{

    private final DepartRepository departRepository;
    private final DepartService departService;
    private final AjaxJson ajaxJson;

    @Autowired
    public DepartController(DepartRepository departRepository, DepartService departService, AjaxJson ajaxJson) {
        this.departRepository = departRepository;
        this.departService = departService;
        this.ajaxJson = ajaxJson;
    }

    @ApiOperation(value="菜單保存接口", notes="保存菜單信息包括新增和修改")
    @PostMapping(value = "/save")
    public AjaxJson save(@RequestBody Depart depart) {
        if(StringUtils.isEmpty(depart.getId())){
            depart = departService.generateOrgCode(depart);
        }
        depart = departRepository.save(depart);
        ajaxJson.setSuccess(true).setStatus(200).setData(depart);
        return ajaxJson;

    }

    @ApiOperation(value="菜單表全部數據", notes="菜單表全部數據")
    @GetMapping(value = "/findAll")
    public AjaxJson findAll() {

        List<Depart> departList = departRepository.findAll();
        if(departList == null || departList.size() == 0){
            return ajaxJson.setSuccess(true).setStatus(200);
        }
        List<Depart> resultMenu = new ArrayList <Depart>();
        for (Depart depart : departList ) {
            if(null != depart.getOrg_code() && depart.getOrg_code().length() == 3 ){
                depart.setChildren(findChildrens(depart.getId(),departList));
                resultMenu.add(depart);
            }
        }
        resultMenu.sort((Depart m1 , Depart m2) -> m1.getOrg_code().compareTo(m2.getOrg_code()));
        return ajaxJson.setSuccess(true).setStatus(200).setData(resultMenu);
    }

    private List<Depart> findChildrens(String pid , List<Depart> departList){

        List<Depart> childrens = new ArrayList <Depart>();
        for (Depart childrenDiction : departList) {

            if(StringUtils.isNotEmpty(pid) && pid.equals(childrenDiction.getParentdepartid())){
                childrens.add(childrenDiction);
            }
        }
        childrens.sort((Depart m1 , Depart m2) -> m1.getOrg_code().compareTo(m2.getOrg_code()));
        return childrens;
    }

    @ApiOperation(value="刪除字典数据", notes="刪除字典数据")
    @DeleteMapping(value="{id}")
    public AjaxJson delete(@PathVariable(value = "id") String id){
        departRepository.delete(id);
        return ajaxJson.setSuccess(true).setStatus(200).setData("数据删除成功！");
    }
}
