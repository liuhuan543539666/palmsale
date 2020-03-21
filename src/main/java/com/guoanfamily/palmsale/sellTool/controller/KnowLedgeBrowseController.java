package com.guoanfamily.palmsale.sellTool.controller;

import com.guoanfamily.palmsale.common.AjaxJson;
import com.guoanfamily.palmsale.common.abstractobj.ApiController;
import com.guoanfamily.palmsale.sellTool.entity.KnowLedgeBrowse;
import com.guoanfamily.palmsale.sellTool.repository.KnowLedgeBrowseRepository;
import com.guoanfamily.palmsale.sellTool.service.KnowLedgeBrowseService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2017/5/22.
 */

@RestController
@RequestMapping(ApiController.KNOWLEDGEBROWSE_URL)
@EnableAutoConfiguration
public class KnowLedgeBrowseController extends ApiController{

    private KnowLedgeBrowseRepository knowLedgeBrowseRepository;
    private AjaxJson ajaxJson;
    private KnowLedgeBrowseService browseSe;

    @Autowired
    public KnowLedgeBrowseController(KnowLedgeBrowseRepository knowLedgeBrowseRepository, AjaxJson ajaxJson,KnowLedgeBrowseService browseSe) {
        this.knowLedgeBrowseRepository = knowLedgeBrowseRepository;
        this.ajaxJson = ajaxJson;
        this.browseSe = browseSe;
    }

    @ApiOperation(value = "saveOrupdate", notes = "房产知识浏览信息")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public AjaxJson save(@RequestBody KnowLedgeBrowse knowLedgeBrowse) {

        knowLedgeBrowse = knowLedgeBrowseRepository.save(knowLedgeBrowse);
        ajaxJson.setSuccess(true).setStatus(200).setData(knowLedgeBrowse);
        System.out.println(ajaxJson);
        return ajaxJson;

    }

    @ApiOperation(value = "查询", notes = "房产知识浏览信息")
    @RequestMapping(value = "/search", method= RequestMethod.GET)
    public AjaxJson search(@RequestParam String id, @RequestParam(value = "page", defaultValue = "0") Integer page,
                                  @RequestParam(value = "size", defaultValue = "10") Integer size){
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(page, size, sort);
        Page<KnowLedgeBrowse> knowLedgeBrowsesList = knowLedgeBrowseRepository.findAll(browseSe.where(id),pageable);
        return ajaxJson.setSuccess(true).setStatus(200).setData(knowLedgeBrowsesList);
    }
}
