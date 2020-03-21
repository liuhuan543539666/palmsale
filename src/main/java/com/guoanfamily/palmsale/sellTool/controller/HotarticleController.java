package com.guoanfamily.palmsale.sellTool.controller;

import com.guoanfamily.palmsale.common.AjaxJson;
import com.guoanfamily.palmsale.common.abstractobj.ApiController;
import com.guoanfamily.palmsale.sellTool.entity.Hotarticle;
import com.guoanfamily.palmsale.sellTool.repository.HotarticleRepository;
import com.guoanfamily.palmsale.sellTool.service.HotarticleService;
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
 * Created by Administrator on 2017/5/25.
 */
@RestController
@RequestMapping(ApiController.HOTARTICLE_URL)
@EnableAutoConfiguration
public class HotarticleController extends ApiController{

    private HotarticleRepository hotarticleRepository;
    private AjaxJson ajaxJson;
    private HotarticleService hotarSe;

    @Autowired
    public HotarticleController(HotarticleRepository hotarticleRepository, AjaxJson ajaxJson, HotarticleService hotarSe) {
        this.hotarticleRepository = hotarticleRepository;
        this.ajaxJson = ajaxJson;
        this.hotarSe = hotarSe;
    }

    @ApiOperation(value = "软文知识save", notes = "软文知识save信息")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public AjaxJson save(@RequestBody Hotarticle hotarticle) {
        // 当前状态为下架  点的是上架  传来的status == 1 改的是上架时间
        if (hotarticle.getId() != null && hotarticle.getStatus() == 1 && hotarticle.getStatus() != null){
            hotarticle.setOnlinetime(KnowLedgeController.getCurrentTime());
        }
        // 当前状态为上架  点的是下架  传来的status == 0 改的是下架时间
        if (hotarticle.getId() != null && hotarticle.getStatus() == 0 && hotarticle.getStatus() != null){
            hotarticle.setOfflinetime(KnowLedgeController.getCurrentTime());
        }
        //新增时多的判断
        if (hotarticle.getId() == null || hotarticle.getId() == ""){
            hotarticle.setIntegral(0);
            hotarticle.setStatus(0);
        }
        hotarticle = hotarticleRepository.save(hotarticle);
        System.out.println(hotarticle);
        ajaxJson.setSuccess(true).setStatus(200).setData(hotarticle);
        return ajaxJson;

    }

    @RequestMapping(value = "/homePage/search", method= RequestMethod.GET)
    public AjaxJson search(@RequestParam Integer status, @RequestParam String title,@RequestParam Integer startIntegral, @RequestParam Integer endIntegral, @RequestParam Integer startbrowsetimes, @RequestParam Integer endbrowsetimes, @RequestParam String startTime, @RequestParam String endTime, @RequestParam String offstartTime, @RequestParam String offendTime, @RequestParam(value = "page", defaultValue = "0") Integer page,
                           @RequestParam(value = "size", defaultValue = "10") Integer size){
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(page, size, sort);
        Page<Hotarticle> hotarticleList = hotarticleRepository.findAll(hotarSe.where(status,title,startIntegral,endIntegral,startbrowsetimes,endbrowsetimes,startTime,endTime,offstartTime,offendTime),pageable);
        return ajaxJson.setSuccess(true).setStatus(200).setData(hotarticleList);
    }
}
