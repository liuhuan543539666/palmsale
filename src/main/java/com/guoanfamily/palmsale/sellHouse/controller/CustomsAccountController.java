package com.guoanfamily.palmsale.sellHouse.controller;

import com.github.wenhao.jpa.Specifications;
import com.guoanfamily.palmsale.common.AjaxJson;
import com.guoanfamily.palmsale.common.abstractobj.ApiController;
import com.guoanfamily.palmsale.sellHouse.entity.AgentInfo;
import com.guoanfamily.palmsale.sellHouse.entity.CustomsFollowManager;
import com.guoanfamily.palmsale.sellHouse.entity.CustomsallotRecords;
import com.guoanfamily.palmsale.sellHouse.entity.Customsllocate;
import com.guoanfamily.palmsale.sellHouse.repository.AgentInfoRepository;
import com.guoanfamily.palmsale.sellHouse.repository.CustomsallotRecordsRepository;
import com.guoanfamily.palmsale.sellHouse.repository.CustomsllocateRepository;
import com.guoanfamily.palmsale.sellHouse.service.CustomsAgentSearchService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.*;

@RestController
@RequestMapping(ApiController.Agent_URL)
public class CustomsAccountController extends ApiController{

    private final AgentInfoRepository agentInfoRepository;
    private final CustomsAgentSearchService seService;
    private final CustomsllocateRepository cus;
    private CustomsallotRecordsRepository crr;
    private AjaxJson ajaxJson;
    @Autowired
    public CustomsAccountController(CustomsallotRecordsRepository crr,AgentInfoRepository agentInfoRepository, AjaxJson ajaxJson, CustomsllocateRepository cus, CustomsAgentSearchService seService) {
        this.agentInfoRepository = agentInfoRepository;
        this.ajaxJson = ajaxJson;
        this.cus = cus;
        this.crr = crr;
        this.seService = seService;
    }

    /**
     * 置业顾问分页查询接口
     * @param page
     * @param size
     * @return AjaxJson
     */
    @ApiOperation(value="置业顾问列表分頁查", notes="置业顾问信息數據")
    @RequestMapping(value = "/findPage", method=RequestMethod.GET)
    public AjaxJson queryAgentInfo(@RequestParam String agentname,  @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                      @RequestParam(value = "size", defaultValue = "10") Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(page, size, sort);
        Specification<AgentInfo> specification = Specifications.<AgentInfo>and()
                .like(StringUtils.isNotBlank(agentname),"agentname" , "%"+agentname+"%")
                .build();
        ajaxJson.setSuccess(true).setStatus(200).setData(agentInfoRepository.findAll(specification,pageable));
        return ajaxJson;
    }

    /**
     * 客户台账详细信息查询
     * @param custname
     * @param phonenumber
     * @param agentid
     * @param signtime
     * @param nuastateid
     * @param page
     * @param size
     * @return
     * @throws UnsupportedEncodingException
     */
    @ApiOperation(value="客户台账详细信息查询接口", notes="客户台账详细信息查询")
    @RequestMapping(value = "/customsSearch", method=RequestMethod.GET)
    public AjaxJson search(@RequestParam String custname, @RequestParam String phonenumber, @RequestParam String agentid, @RequestParam String signtime, @RequestParam String nuastateid, @RequestParam(value = "page", defaultValue = "0") Integer page,
                           @RequestParam(value = "size", defaultValue = "10") Integer size) throws UnsupportedEncodingException {
        Sort sort = new Sort(Sort.Direction.DESC, "createtime");
        Pageable pageable = new PageRequest(page, size, sort);
        ajaxJson.setSuccess(true).setStatus(200).setData(cus.findAll(seService.where(custname, phonenumber, agentid, signtime, nuastateid),pageable));
        return ajaxJson;
    }

    /**
     * 客户台账分配置业顾问记录save接口
     * @param partnerInfo
     * @return
     */
    @ApiOperation(value="客户台账分配置业顾问记录save接口", notes="客户台账分配置业顾问记录save")
    @RequestMapping(value = "/saveAllotRecords", method = RequestMethod.POST)
    public AjaxJson save(@RequestBody CustomsallotRecords partnerInfo) {
        partnerInfo = crr.save(partnerInfo);
        HashMap map = new HashMap<>();
        map.put("flag","success");
        ajaxJson.setSuccess(true).setStatus(200).setData(map);
        return ajaxJson;
    }

    /**
     * 置业顾问对应各阶段客户数目查询接口
     * @param agentid
     * @return
     * @throws UnsupportedEncodingException
     */
    @ApiOperation(value="置业顾问对应各阶段客户数目查询接口", notes="置业顾问对应各阶段客户数目查询")
    @RequestMapping(value = "/totalCust", method=RequestMethod.GET)
    public AjaxJson totalCust(@RequestParam String agentid) throws UnsupportedEncodingException {
        List<Customsllocate> custList = cus.findAgentId(agentid);
        List<String> resultMenu = new ArrayList<>();
        int firstCount = 0;
        int followUpCount = 0;
        int custOrderCount = 0;
        int intentionCount = 0;
        HashMap map = new HashMap<>();
        for (Customsllocate depart : custList ) {
            //获取跟进人数
            if(depart.getcustomsFollowManager() != null && depart.getcustomsFollowManager().size() != 0 && depart.getPrjthobby() == null && depart.getcustOrder() == null) {
                followUpCount ++;
            }
            //获取补充意向信息人数
            else if(depart.getPrjthobby() != null && depart.getcustOrder() == null) {
                intentionCount ++;
            }
            //获取定金入会人数
            else if(depart.getcustOrder() != null) {
                custOrderCount ++;
            }
            //获取首访客户人数
            else{
                firstCount ++;
            }
        }
        map.put("firstCount",firstCount);
        map.put("followUpCount",followUpCount);
        map.put("custOrderCount",custOrderCount);
        map.put("intentionCount",intentionCount);
        ajaxJson.setSuccess(true).setStatus(200).setData(map);
        return ajaxJson;
    }
}