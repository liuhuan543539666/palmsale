package com.guoanfamily.palmsale.sellHouse.controller;

import com.guoanfamily.palmsale.common.AjaxJson;
import com.guoanfamily.palmsale.common.abstractobj.ApiController;
import com.guoanfamily.palmsale.sellHouse.entity.Customsllocate;
import com.guoanfamily.palmsale.sellHouse.repository.CustomsllocateRepository;
import com.guoanfamily.palmsale.sellHouse.service.CustomsllocateReplenishService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

@RestController
@RequestMapping(ApiController.CUST_URL)
public class CustomsllocateReplenishController extends ApiController{

    private final CustomsllocateRepository customsllocateRepository;
    private AjaxJson ajaxJson;
    private final CustomsllocateReplenishService seService;
    private final CustomsllocateRepository cus;
    @Autowired
    public CustomsllocateReplenishController(CustomsllocateRepository customsllocateRepository, AjaxJson ajaxJson, CustomsllocateReplenishService seService, CustomsllocateRepository cus) {
        this.customsllocateRepository = customsllocateRepository;
        this.ajaxJson = ajaxJson;
        this.seService = seService;
        this.cus = cus;
    }

    /**
     *  补充意向信息新增接口
     * @param partnerInfo
     * @return AjaxJson
     */
    @ApiOperation(value="补充意向户信息save接口", notes="补充意向信息")
    @RequestMapping(value = "/replenish/save", method = RequestMethod.POST)
    public AjaxJson save(@RequestBody Customsllocate partnerInfo) {
        partnerInfo = customsllocateRepository.save(partnerInfo);
        HashMap map = new HashMap<>();
        map.put("flag","success");
        ajaxJson.setSuccess(true).setStatus(200).setData(map);
        return ajaxJson;
    }

    /**
     * 补充意向查询查询接口
     * @param custname
     * @param sDate
     * @param eDate
     * @param agentid
     * @param intentionlevel
     * @param channeltype
     * @param page
     * @param size
     * @return AjaxJson
     * @throws UnsupportedEncodingException
     */
    @ApiOperation(value="补充意向户信息动态查询接口", notes="补充意向信息动态查询接口")
    @RequestMapping(value = "/replenish/search", method=RequestMethod.GET)
    public AjaxJson search(HttpServletRequest request, @RequestParam String custname, @RequestParam String sDate, @RequestParam String eDate, @RequestParam String agentid, @RequestParam String intentionlevel, @RequestParam String channeltype, @RequestParam(value = "page", defaultValue = "0") Integer page,
                           @RequestParam(value = "size", defaultValue = "10") Integer size) throws UnsupportedEncodingException {
        Sort sort = new Sort(Sort.Direction.DESC, "createtime");
        Pageable pageable = new PageRequest(page, size, sort);
        ajaxJson.setSuccess(true).setStatus(200).setData(cus.findAll(seService.where(custname, sDate, eDate, agentid, intentionlevel, channeltype),pageable));
        return ajaxJson;
    }
}
