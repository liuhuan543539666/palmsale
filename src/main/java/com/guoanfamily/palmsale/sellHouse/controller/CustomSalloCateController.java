package com.guoanfamily.palmsale.sellHouse.controller;

import com.guoanfamily.palmsale.common.AjaxJson;
import com.guoanfamily.palmsale.common.abstractobj.ApiController;
import com.guoanfamily.palmsale.sellHouse.entity.CustomSalloCate;
import com.guoanfamily.palmsale.sellHouse.repository.CustomSalloCateRepository;
import com.guoanfamily.palmsale.sellHouse.service.CustomsllocateSearchServices;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2017/5/5.
 */
@RestController
@RequestMapping(ApiController.CUSTOMSALLOCATE_URL)
@EnableAutoConfiguration
public class CustomSalloCateController extends ApiController{

    private CustomSalloCateRepository customSalloCateRepository;
    private AjaxJson ajaxJson;
    private CustomsllocateSearchServices seService;

    @Autowired
    public CustomSalloCateController(CustomSalloCateRepository customSalloCateRepository, AjaxJson ajaxJson, CustomsllocateSearchServices seService) {
        this.customSalloCateRepository = customSalloCateRepository;
        this.ajaxJson = ajaxJson;
        this.seService =  seService;
    }

    @ApiOperation(value = "客户save信息", notes = "")
    @RequestMapping(value = "/savecustomSalloCate", method = RequestMethod.POST)
    public AjaxJson saveCustomerInfo(@RequestBody CustomSalloCate customerInfo) {
        customerInfo = customSalloCateRepository.save(customerInfo);
        ajaxJson.setSuccess(true).setStatus(200).setData(customerInfo);
        return ajaxJson;
    }

    @ApiOperation(value = "成交管理首页查询", notes = "")
    @RequestMapping(value = "/search", method= RequestMethod.GET)
    public AjaxJson search(@RequestParam String custname, @RequestParam String phonenumber,@RequestParam String agentid,@RequestParam String startTime,@RequestParam String endTime,@RequestParam Integer dealstatus,  @RequestParam(value = "page", defaultValue = "0") Integer page,
                           @RequestParam(value = "size", defaultValue = "10") Integer size){
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(page, size, sort);
        return ajaxJson.setSuccess(true).setStatus(200).setData(customSalloCateRepository.findAll(seService.where(custname,phonenumber,agentid,startTime,endTime,dealstatus),pageable));
    }


}
