package com.guoanfamily.palmsale.system.controller;

import com.guoanfamily.palmsale.common.AjaxJson;
import com.guoanfamily.palmsale.common.abstractobj.ApiController;
import com.guoanfamily.palmsale.system.entity.MenuFunction;
import com.guoanfamily.palmsale.system.repository.MenuRepository;
import com.guoanfamily.palmsale.system.service.MenuServiceImp;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017/4/25.
 */
@RestController
@RequestMapping(ApiController.MENU_URL)
public class MenuController extends ApiController{

    private final MenuRepository menuRepository;
    private final AjaxJson ajaxJson;
    private final MenuServiceImp menuServiceImp;

    @Autowired
    public MenuController(MenuRepository menuRepository, AjaxJson ajaxJson, MenuServiceImp menuServiceImp) {
        this.menuRepository = menuRepository;
        this.ajaxJson = ajaxJson;
        this.menuServiceImp = menuServiceImp;
    }

    @ApiOperation(value="菜單保存接口", notes="保存菜單信息包括新增和修改")
    @PostMapping(value = "/save")
    public AjaxJson save(@RequestBody MenuFunction menuInfo) {
        menuInfo = menuRepository.save(menuInfo);
        ajaxJson.setSuccess(true).setStatus(200).setData(menuInfo);
        return ajaxJson;

    }

    @ApiOperation(value="菜單表分頁查", notes="分页显示菜單数据")
    @GetMapping(value = "/findPage")
    public AjaxJson getEntryByPageable(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                      @RequestParam(value = "size", defaultValue = "2") Integer size) {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        authentication.getCredentials();
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(page, size, sort);
        return ajaxJson.setSuccess(true).setStatus(200).setData(menuRepository.findAll(pageable));
    }

    @ApiOperation(value="菜單表全部數據", notes="菜單表全部數據")
    @GetMapping(value = "/findAll")
    public AjaxJson findAll() {

        List<MenuFunction> menuFunctions = menuRepository.findAll();
        if(menuFunctions == null || menuFunctions.size() == 0){
            return ajaxJson.setSuccess(true).setStatus(200);
        }
        Integer maxLevel = menuRepository.getMaxLevel() == null ? 0 : menuRepository.getMaxLevel();
        List<MenuFunction> resultMenu = new ArrayList <MenuFunction>();
        for (MenuFunction menuFunction : menuFunctions ) {
            if(null == menuFunction.getLevel()){
                menuFunction.setLevel(new Long(0));
            }
            if(menuFunction.getLevel() < maxLevel){
                menuFunction.setChildren(findChildrens(menuFunction.getId(),menuFunctions));
            }
            if (menuFunction.getLevel() == 0){
                resultMenu.add(menuFunction);
            }
        }
        resultMenu.sort((MenuFunction m1 , MenuFunction m2) -> m1.getFunctionorder().compareTo(m2.getFunctionorder()));
        return ajaxJson.setSuccess(true).setStatus(200).setData(resultMenu);
    }

    private List<MenuFunction> findChildrens(String parentId , List<MenuFunction> menuFunctions){

        List<MenuFunction> childrens = new ArrayList <MenuFunction>();
        for (MenuFunction childrenMenu : menuFunctions) {

            if(StringUtils.isNotEmpty(parentId) && parentId.equals(childrenMenu.getParent())){
                childrens.add(childrenMenu);
            }
        }
        childrens.sort((MenuFunction m1 , MenuFunction m2) -> m1.getFunctionorder().compareTo(m2.getFunctionorder()));
        return childrens;
    }

    @ApiOperation(value="刪除菜單", notes="刪除菜單")
    @DeleteMapping(value="{id}")
    public AjaxJson delete(@PathVariable(value = "id") String id){
//        menuRepository.delete(id);
        try {
            menuServiceImp.removeMentAndRole(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ajaxJson.setSuccess(true).setStatus(200).setData("数据删除成功！");
    }
}
