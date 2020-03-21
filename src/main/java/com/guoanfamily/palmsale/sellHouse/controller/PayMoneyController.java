package com.guoanfamily.palmsale.sellHouse.controller;

import com.guoanfamily.palmsale.common.AjaxJson;
import com.guoanfamily.palmsale.common.abstractobj.ApiController;
import com.guoanfamily.palmsale.sellHouse.entity.CustomerDealInfo;
import com.guoanfamily.palmsale.sellHouse.repository.PayMoneyRepository;
import com.guoanfamily.palmsale.sellHouse.service.PayMoneyService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * Created by Administrator on 2017/6/1.
 */
@RestController
@RequestMapping(ApiController.PAYMONEY_URL)
@EnableAutoConfiguration
public class PayMoneyController extends ApiController{

    private  PayMoneyRepository payMoneyRepository;
    private  AjaxJson ajaxJson;
    private  PayMoneyService payMoneyService;

    @Autowired
    public PayMoneyController(PayMoneyService payMoneyService, AjaxJson ajaxJson, PayMoneyRepository payMoneyRepository) {
        this.payMoneyRepository = payMoneyRepository;
        this.ajaxJson = ajaxJson;
        this.payMoneyService = payMoneyService;
    }

    @ApiOperation(value = "web佣金管理首页查询", notes = "佣金管理分页")
    @RequestMapping(value = "/payMoney/commissionSelect", method= RequestMethod.GET)
    public AjaxJson commissionSelect(@RequestParam String custname, @RequestParam String phonenumber, @RequestParam String agentid, @RequestParam String startTime, @RequestParam String endTime,@RequestParam Integer startMoney,@RequestParam Integer endMoney,@RequestParam Integer paystate, @RequestParam(value = "page", defaultValue = "0") Integer page,
                                     @RequestParam(value = "size", defaultValue = "10") Integer size){
        Sort sort = new Sort(Sort.Direction.DESC, "dealdate");
        Pageable pageable = new PageRequest(page, size, sort);
        Page<CustomerDealInfo> payMoneyList = payMoneyRepository.findAll(payMoneyService.where(custname,phonenumber,agentid,startTime,endTime,startMoney,endMoney,paystate),pageable);
        return ajaxJson.setSuccess(true).setStatus(200).setData(payMoneyList);
    }


    @ApiOperation(value = "我的佣金接口", notes = "我的佣金查询")
    @RequestMapping(value = "/payMoney/mypayMoney", method= RequestMethod.GET)
    public AjaxJson mypayMoney(@RequestParam String custname, @RequestParam String phonenumber, @RequestParam String agentid, @RequestParam String startTime, @RequestParam String endTime,@RequestParam Integer startMoney,@RequestParam Integer endMoney,@RequestParam Integer paystate, @RequestParam(value = "page", defaultValue = "0") Integer page,
                                     @RequestParam(value = "size", defaultValue = "10") Integer size){
        Sort sort = new Sort(Sort.Direction.ASC, "dealdate");
        Pageable pageable = new PageRequest(page, size, sort);
        Page<CustomerDealInfo> payMoneyList = payMoneyRepository.findAll(payMoneyService.where(custname,phonenumber,agentid,startTime,endTime,startMoney,endMoney,paystate),pageable);
        return ajaxJson.setSuccess(true).setStatus(200).setData(payMoneyList);
    }

}
