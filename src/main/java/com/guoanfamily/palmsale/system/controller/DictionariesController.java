package com.guoanfamily.palmsale.system.controller;

import com.guoanfamily.palmsale.common.AjaxJson;
import com.guoanfamily.palmsale.common.abstractobj.ApiController;
import com.guoanfamily.palmsale.system.entity.DictionariesEntity;
import com.guoanfamily.palmsale.system.entity.MenuFunction;
import com.guoanfamily.palmsale.system.repository.DictionariesRepository;
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
@RequestMapping(ApiController.DICT_URL)
public class DictionariesController extends ApiController{

    private final DictionariesRepository dictionariesRepository;
    private final AjaxJson ajaxJson;

    @Autowired
    public DictionariesController(DictionariesRepository dictionariesRepository, AjaxJson ajaxJson) {
        this.dictionariesRepository = dictionariesRepository;
        this.ajaxJson = ajaxJson;

    }

    @ApiOperation(value="菜單保存接口", notes="保存菜單信息包括新增和修改")
    @PostMapping(value = "/save")
    public AjaxJson save(@RequestBody DictionariesEntity dictionariesEntity) {
        dictionariesEntity = dictionariesRepository.save(dictionariesEntity);
        ajaxJson.setSuccess(true).setStatus(200).setData(dictionariesEntity);
        return ajaxJson;

    }

    @ApiOperation(value="菜單表全部數據", notes="菜單表全部數據")
    @GetMapping(value = "/findAll")
    public AjaxJson findAll() {

        List<DictionariesEntity> dictionariesList = dictionariesRepository.findAll();
        if(dictionariesList == null || dictionariesList.size() == 0){
            return ajaxJson.setSuccess(true).setStatus(200);
        }
        List<DictionariesEntity> resultMenu = new ArrayList <DictionariesEntity>();
        for (DictionariesEntity dictionariesEntity : dictionariesList ) {
            if(null == dictionariesEntity.getDictno()){
                dictionariesEntity.setDictno("0");
            }
            dictionariesEntity.setChildren(findChildrens(dictionariesEntity.getId(),dictionariesList));
            if (StringUtils.isEmpty(dictionariesEntity.getPid())){
                resultMenu.add(dictionariesEntity);
            }
        }
        resultMenu.sort((DictionariesEntity m1 , DictionariesEntity m2) -> m1.getDictno().compareTo(m2.getDictno()));
        return ajaxJson.setSuccess(true).setStatus(200).setData(resultMenu);
    }

    private List<DictionariesEntity> findChildrens(String pid , List<DictionariesEntity> dictionariesEntityList){

        List<DictionariesEntity> childrens = new ArrayList <DictionariesEntity>();
        for (DictionariesEntity childrenDiction : dictionariesEntityList) {
            if(null == childrenDiction.getDictno()){
                childrenDiction.setDictno("0");
            }
            if(StringUtils.isNotEmpty(pid) && pid.equals(childrenDiction.getPid())){
                childrens.add(childrenDiction);
            }
        }
        childrens.sort((DictionariesEntity m1 , DictionariesEntity m2) -> m1.getDictno().compareTo(m2.getDictno()));
        return childrens;
    }

    @ApiOperation(value="刪除字典数据", notes="刪除字典数据")
    @DeleteMapping(value="{id}")
    public AjaxJson delete(@PathVariable(value = "id") String id){
        dictionariesRepository.delete(id);
        return ajaxJson.setSuccess(true).setStatus(200).setData("数据删除成功！");
    }

}
