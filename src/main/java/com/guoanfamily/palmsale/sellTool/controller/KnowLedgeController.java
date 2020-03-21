package com.guoanfamily.palmsale.sellTool.controller;

import com.guoanfamily.palmsale.common.AjaxJson;
import com.guoanfamily.palmsale.common.abstractobj.ApiController;
import com.guoanfamily.palmsale.sellTool.entity.KnowLedge;
import com.guoanfamily.palmsale.sellTool.repository.KnowLedgeRepository;
import com.guoanfamily.palmsale.sellTool.service.KnowLedgeSearchService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/5/22.
 */

@RestController
@RequestMapping(ApiController.KNOWLEDGE_URL)
@EnableAutoConfiguration
public class KnowLedgeController extends  ApiController{

    private KnowLedgeRepository knowLedgeRepository;
    private AjaxJson ajaxJson;
    private KnowLedgeSearchService knowSes;

    @Autowired
    public KnowLedgeController(KnowLedgeRepository knowLedgeRepository, AjaxJson ajaxJson,KnowLedgeSearchService knowSes) {
        this.knowLedgeRepository = knowLedgeRepository;
        this.ajaxJson = ajaxJson;
        this.knowSes = knowSes;
    }

    @ApiOperation(value = "房产知识save", notes = "房产知识信息")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public AjaxJson save(@RequestBody KnowLedge knowLedge) {
        // 当前状态为下架  点的是上架  传来的status == 1 改的是上架时间
        if (knowLedge.getId() != null && knowLedge.getStatus() == 1 && knowLedge.getStatus() != null){
            knowLedge.setOnlinetime(getCurrentTime());
        }
        // 当前状态为上架  点的是下架  传来的status == 0 改的是下架时间
        if (knowLedge.getId() != null && knowLedge.getStatus() == 0 && knowLedge.getStatus() != null){
            knowLedge.setOfflinetime(getCurrentTime());
        }
        //新增时多的判断
        if (knowLedge.getId() == null || knowLedge.getId() == ""){
            knowLedge.setBrowsetimes(0L);
            knowLedge.setStatus(0L);
        }
        knowLedge = knowLedgeRepository.save(knowLedge);
        ajaxJson.setSuccess(true).setStatus(200).setData(knowLedge);
        System.out.println(ajaxJson);
        return ajaxJson;

    }

    @RequestMapping(value = "/search", method= RequestMethod.GET)
    public AjaxJson search(@RequestParam String classifyid, @RequestParam String title, @RequestParam Integer startbrowsetimes, @RequestParam Integer endbrowsetimes,@RequestParam String startTime, @RequestParam String endTime,@RequestParam String offstartTime, @RequestParam String offendTime, @RequestParam(value = "page", defaultValue = "0") Integer page,
                                         @RequestParam(value = "size", defaultValue = "10") Integer size){
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(page, size, sort);
        Page<KnowLedge> knowLedgeList = knowLedgeRepository.findAll(knowSes.where(classifyid,title,startbrowsetimes,endbrowsetimes,startTime,endTime,offstartTime,offendTime),pageable);
        return ajaxJson.setSuccess(true).setStatus(200).setData(knowLedgeList);
    }

    //获取当前时间 "yyyy-MM-dd HH:mm:ss"
    public static Timestamp getCurrentTime(){
        String current = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format( new Date());
        Timestamp time = Timestamp.valueOf(current);
        return time;
    }
}
