package com.guoanfamily.palmsale.sellHouse.controller;

import com.guoanfamily.palmsale.common.AjaxJson;
import com.guoanfamily.palmsale.common.abstractobj.ApiController;
import com.guoanfamily.palmsale.sellHouse.entity.PayMoneyRecord;
import com.guoanfamily.palmsale.sellHouse.repository.PayMoneyRecordRepository;
import com.guoanfamily.palmsale.sellHouse.service.PayMoneyRecordService;
import com.guoanfamily.palmsale.sellTool.controller.KnowLedgeController;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/6/5.
 */
@RestController
@RequestMapping(ApiController.PAYMONEY_URL)
@EnableAutoConfiguration
public class PayMoneyRecordController extends ApiController{

    private PayMoneyRecordRepository payMoneyRecordRepository;
    private PayMoneyRecordService payMoneyRecordService;
    private AjaxJson ajaxJson;

    /**
     *
     * @param payMoneyRecordRepository
     * @param payMoneyRecordService
     * @param ajaxJson
     */
    @Autowired
    public PayMoneyRecordController(PayMoneyRecordRepository payMoneyRecordRepository, PayMoneyRecordService payMoneyRecordService, AjaxJson ajaxJson) {
        this.payMoneyRecordRepository = payMoneyRecordRepository;
        this.payMoneyRecordService = payMoneyRecordService;
        this.ajaxJson = ajaxJson;
    }

    /**
     *
     * @param payMoneyRecord
     * @return
     */
    @ApiOperation(value="佣金记录save接口", notes="佣金信息")
    @RequestMapping(value = "/paymoneyRecord/save", method = RequestMethod.POST)
    public AjaxJson save(@RequestBody PayMoneyRecord payMoneyRecord) {
        if (payMoneyRecord.getId() == null || payMoneyRecord.getId() == "" ){
            payMoneyRecord.setPaymoneydate(KnowLedgeController.getCurrentTime());
        }
        payMoneyRecord = payMoneyRecordRepository.save(payMoneyRecord);
        ajaxJson.setSuccess(true).setStatus(200).setData(payMoneyRecord);
        return ajaxJson;
    }


    @ApiOperation(value="佣金记录查询", notes="佣金信息")
    @RequestMapping(value = "/paymoneyRecord/search", method= RequestMethod.GET)
    public AjaxJson search(@RequestParam String dealpaymoneyid, @RequestParam(value = "page", defaultValue = "0") Integer page,
                           @RequestParam(value = "size", defaultValue = "10") Integer size){
        Sort sort = new Sort(Sort.Direction.ASC, "paymoneydate");
        org.springframework.data.domain.Pageable pageable = new PageRequest(page, size, sort);
        Page<PayMoneyRecord> payMoneyRecordList = payMoneyRecordRepository.findAll(payMoneyRecordService.where(dealpaymoneyid),pageable);
        return ajaxJson.setSuccess(true).setStatus(200).setData(payMoneyRecordList);
    }


}
