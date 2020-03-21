package com.guoanfamily.palmsale.sellHouse.controller;

import com.guoanfamily.palmsale.common.AjaxJson;
import com.guoanfamily.palmsale.common.abstractobj.ApiController;
import com.guoanfamily.palmsale.sellHouse.entity.CustomsFollowManager;
import com.guoanfamily.palmsale.sellHouse.repository.CustomsFollowManagerRepository;
import com.guoanfamily.palmsale.sellHouse.repository.CustomsHistoryGroupByRepository;
import com.guoanfamily.palmsale.sellHouse.service.CustomsFollowManagerGroupByService;
import com.guoanfamily.palmsale.sellHouse.service.CustomsFollowUpSearchService;
import io.swagger.annotations.ApiOperation;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

@RestController
@RequestMapping(ApiController.CUST_URL)
public class CustomsFollowManagerController extends ApiController{

    private CustomsHistoryGroupByRepository customGroup;
    private AjaxJson ajaxJson;
    private CustomsFollowManagerGroupByService seService;
    private CustomsFollowManagerRepository cus;
    private CustomsFollowUpSearchService cusFollowUp;
    @Autowired
    public CustomsFollowManagerController(CustomsHistoryGroupByRepository customGroup, AjaxJson ajaxJson, CustomsFollowManagerGroupByService seService, CustomsFollowManagerRepository cus, CustomsFollowUpSearchService cusFollowUp) {
        this.customGroup = customGroup;
        this.ajaxJson = ajaxJson;
        this.seService = seService;
        this.cus = cus;
        this.cusFollowUp = cusFollowUp;
    }

    /**
     * 客户跟进信息分组查询接口
     * @param custname
     * @param startDate
     * @param endDate
     * @param agentid
     * @param intentionlevel
     * @param channeltype
     * @param followType
     * @param mincount
     * @param maxcount
     * @param page
     * @param size
     * @return AjaxJson
     */
    @ApiOperation(value="客户跟进信息分组查询", notes="客户跟进信息分组查询")
    @RequestMapping(value = "/custFollow/searchGruopBy", method=RequestMethod.GET)
    public AjaxJson searchGruopBy(@RequestParam String custname, @RequestParam String startDate, @RequestParam String endDate, @RequestParam String agentid,
                                  @RequestParam String intentionlevel, @RequestParam String channeltype, @RequestParam String followType, @RequestParam String mincount,@RequestParam String maxcount,
                                  @RequestParam(value = "page", defaultValue = "0") Integer page,
                                       @RequestParam(value = "size", defaultValue = "10") Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC, "createtime");
        Pageable pageable = new PageRequest(page, size, sort);
        ajaxJson.setSuccess(true).setStatus(200).setData(customGroup.findAll(seService.where(custname, startDate, endDate, agentid, intentionlevel, channeltype, followType, mincount, maxcount),pageable));
        return ajaxJson;
    }

    /**
     *  客户跟进信息新增接口
     * @param partnerInfo
     * @return AjaxJson
     */
    @ApiOperation(value="客户跟进信息save接口", notes="客户跟进信息")
    @RequestMapping(value = "/custFollow/save", method = RequestMethod.POST)
    public AjaxJson save(@RequestBody CustomsFollowManager partnerInfo) {
        //存储跟进时间
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        partnerInfo.setCreatetime(java.sql.Timestamp.valueOf(String.valueOf(df.format(new Date()))));
        partnerInfo = cus.save(partnerInfo);
        HashMap map = new HashMap<>();
        map.put("flag","success");
        ajaxJson.setSuccess(true).setStatus(200).setData(map);
        return ajaxJson;
    }

    @ApiOperation(value="客户跟进信息详细记录查询", notes="客户跟进信息详细记录查询")
    @RequestMapping(value = "/custFollow/followUpSearch", method=RequestMethod.GET)
    public AjaxJson followUpSearch(@RequestParam String custname, @RequestParam String customid,
                                  @RequestParam(value = "page", defaultValue = "0") Integer page,
                                  @RequestParam(value = "size", defaultValue = "10") Integer size) throws JSONException {
        Sort sort = new Sort(Sort.Direction.DESC, "createtime");
        Pageable pageable = new PageRequest(page, size, sort);
        ajaxJson.setSuccess(true).setStatus(200).setData(cus.findAll(cusFollowUp.where(custname, customid),pageable));
        return ajaxJson;
    }
}
