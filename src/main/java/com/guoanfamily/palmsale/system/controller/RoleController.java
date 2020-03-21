package com.guoanfamily.palmsale.system.controller;

import com.guoanfamily.palmsale.common.AjaxJson;
import com.guoanfamily.palmsale.common.abstractobj.ApiController;
import com.guoanfamily.palmsale.system.entity.Role;
import com.guoanfamily.palmsale.system.repository.RoleRepository;
import com.guoanfamily.palmsale.system.service.RoleServiceImp;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

/**
 * Created by lenovo on 2017/4/25.
 */
@RestController
@RequestMapping(ApiController.ROLE_URL)
public class RoleController extends ApiController{

    private final RoleRepository roleRepository;
    private final RoleServiceImp roleServiceImp;
    private final AjaxJson ajaxJson;

    @Autowired
    public RoleController(RoleRepository roleRepository, RoleServiceImp roleServiceImp, AjaxJson ajaxJson) {
        this.roleRepository = roleRepository;
        this.roleServiceImp = roleServiceImp;
        this.ajaxJson = ajaxJson;
    }

    @ApiOperation(value="角色保存接口", notes="保存角色信息包括新增和修改")
    @PostMapping(value = "/save")
    public AjaxJson save(@RequestBody Role roleInfo) {

        roleInfo = roleRepository.save(roleInfo);
        ajaxJson.setSuccess(true).setStatus(200).setData(roleInfo);
        return ajaxJson;

    }

    @ApiOperation(value="角色表分頁查", notes="分页显示角色数据")
    @GetMapping(value = "/findPage")
    public AjaxJson getEntryByPageable(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                         @RequestParam(value = "size", defaultValue = "2") Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(page, size, sort);
        return ajaxJson.setSuccess(true).setStatus(200).setData(roleRepository.findAll(pageable));
//        return roleRepository.findAll(pageable);
    }

    @ApiOperation(value="查询角色全部数据", notes="查询角色全部数据")
    @GetMapping(value = "/findAll")
    public AjaxJson findAll() {
        return ajaxJson.setSuccess(true).setStatus(200).setData(roleRepository.findAll());
    }

    @DeleteMapping(value="{id}")
    public AjaxJson delete(@PathVariable(value = "id") String id){
//        roleRepository.delete(id);
        roleServiceImp.removeRoleAndMenu(id);
        return ajaxJson.setSuccess(true).setStatus(200).setData("数据删除成功！");
    }
}
