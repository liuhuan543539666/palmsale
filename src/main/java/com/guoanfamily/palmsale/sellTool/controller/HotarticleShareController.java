package com.guoanfamily.palmsale.sellTool.controller;

import com.guoanfamily.palmsale.common.AjaxJson;
import com.guoanfamily.palmsale.common.abstractobj.ApiController;
import com.guoanfamily.palmsale.sellTool.entity.HotarticleShare;
import com.guoanfamily.palmsale.sellTool.entity.KnowLedgeBrowse;
import com.guoanfamily.palmsale.sellTool.repository.HotarticleShareRepository;
import com.guoanfamily.palmsale.sellTool.service.HotarticleShareService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2017/5/25.
 */
@RestController
@RequestMapping(ApiController.HOTARTICLE_URL)
@EnableAutoConfiguration
public class HotarticleShareController extends ApiController {

    private HotarticleShareRepository hotarticleShareRepository;
    private AjaxJson ajaxJson;
    private HotarticleShareService hotShse;

    @Autowired
    public HotarticleShareController(HotarticleShareRepository hotarticleShareRepository, AjaxJson ajaxJson, HotarticleShareService hotShse) {
        this.hotarticleShareRepository = hotarticleShareRepository;
        this.ajaxJson = ajaxJson;
        this.hotShse = hotShse;
    }

    @RequestMapping(value = "/sharePage/search", method= RequestMethod.GET)
    public AjaxJson search(@RequestParam String id, @RequestParam(value = "page", defaultValue = "0") Integer page,
                           @RequestParam(value = "size", defaultValue = "10") Integer size){
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        org.springframework.data.domain.Pageable pageable = new PageRequest(page, size, sort);
        Page<HotarticleShare> hotartShareList = hotarticleShareRepository.findAll(hotShse.where(id),pageable);
        return ajaxJson.setSuccess(true).setStatus(200).setData(hotartShareList);
    }

    @ApiOperation(value = "", notes = "聚焦软文积分设置")
    @RequestMapping(value = "/sharePage/save", method = RequestMethod.POST)
    public AjaxJson save(@RequestBody HotarticleShare hotarticleShare) {

        hotarticleShare = hotarticleShareRepository.save(hotarticleShare);
        ajaxJson.setSuccess(true).setStatus(200).setData(hotarticleShare);
        System.out.println(ajaxJson);
        return ajaxJson;

    }


}
