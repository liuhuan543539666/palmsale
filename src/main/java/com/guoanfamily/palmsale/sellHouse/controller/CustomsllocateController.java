package com.guoanfamily.palmsale.sellHouse.controller;

import com.guoanfamily.palmsale.common.AjaxJson;
import com.guoanfamily.palmsale.common.abstractobj.ApiController;
import com.guoanfamily.palmsale.sellHouse.entity.Customsllocate;
import com.guoanfamily.palmsale.sellHouse.repository.CustomsllocateRepository;
import com.guoanfamily.palmsale.sellHouse.service.CustomsllocateSearchService;
import com.sun.deploy.net.HttpRequest;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(ApiController.CUST_URL)
public class CustomsllocateController extends ApiController{

    private final CustomsllocateRepository customsllocateRepository;
    private AjaxJson ajaxJson;
    private final CustomsllocateSearchService seService;
    private final CustomsllocateRepository cus;
    @Autowired
    public CustomsllocateController(CustomsllocateRepository customsllocateRepository, AjaxJson ajaxJson, CustomsllocateSearchService seService, CustomsllocateRepository cus) {
        this.customsllocateRepository = customsllocateRepository;
        this.ajaxJson = ajaxJson;
        this.seService = seService;
        this.cus = cus;
    }

    /**
     *  首访客户信息新增接口
     * @param partnerInfo
     * @return AjaxJson
     */
    @ApiOperation(value="首访客户信息save接口", notes="首访客户信息")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public AjaxJson save(@RequestBody Customsllocate partnerInfo) {
        //存储创建时间
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        partnerInfo.setCreatetime(java.sql.Timestamp.valueOf(String.valueOf(df.format(new Date()))));
        partnerInfo = customsllocateRepository.save(partnerInfo);
        HashMap map = new HashMap<>();
        map.put("flag","success");
        ajaxJson.setSuccess(true).setStatus(200).setData(map);
        return ajaxJson;
    }

    /**
     * 首访客户信息分页查询接口
     * @param page
     * @param size
     * @return AjaxJson
     */
    @ApiOperation(value="首访客户信息列表分頁查", notes="分頁顯示首访客户信息數據")
    @RequestMapping(value = "/findPage", method=RequestMethod.GET)
    public AjaxJson getEntryByPageable(@RequestParam String custname, @RequestParam String intentionlevel,@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                      @RequestParam(value = "size", defaultValue = "10") Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC, "createtime");
        Pageable pageable = new PageRequest(page, size, sort);
        if(intentionlevel == null || intentionlevel.equals("") && custname != null && !custname.equals("")) {
            ajaxJson.setSuccess(true).setStatus(200).setData(customsllocateRepository.findByCustname(custname, pageable));
        }else if(custname == null || custname.equals("") && intentionlevel != null && !intentionlevel.equals("")){
            String level = null;
            if(intentionlevel.contains(" ")) {
                level = intentionlevel.trim() + "+";
            }else{
                level = intentionlevel;
            }
            ajaxJson.setSuccess(true).setStatus(200).setData(customsllocateRepository.findIntentionlevel(level, pageable));
        }else{
            ajaxJson.setSuccess(true).setStatus(200).setData(customsllocateRepository.findAll(pageable));
        }
        return ajaxJson;
    }

    /**
     * 首访客户信息删除接口
     * @param id
     * @return AjaxJson
     */
    @DeleteMapping(value="{id}")
    public AjaxJson delete(@PathVariable(value = "id") String id){
        customsllocateRepository.delete(id);
        return ajaxJson.setSuccess(true).setData("数据删除成功！");
    }

    /**
     * 首访客户信息动态查询接口
     * @param custname
     * @param sex
     * @param phonenumber
     * @param channeltype
     * @param intetionbuildid
     * @param page
     * @param size
     * @return AjaxJson
     */
    @ApiOperation(value="首访客户信息动态查询", notes="动态查询首访客户信息")
    @RequestMapping(value = "/search", method=RequestMethod.GET)
    public AjaxJson search(HttpServletRequest request, @RequestParam String custname, @RequestParam String sex, @RequestParam String phonenumber, @RequestParam String channeltype, @RequestParam String intetionbuildid, @RequestParam(value = "page", defaultValue = "0") Integer page,
                           @RequestParam(value = "size", defaultValue = "10") Integer size) throws UnsupportedEncodingException {
        //String userId = (String) request.getAttribute("userId");
        Sort sort = new Sort(Sort.Direction.DESC, "createtime");
        Pageable pageable = new PageRequest(page, size, sort);
        ajaxJson.setSuccess(true).setStatus(200).setData(cus.findAll(seService.where(custname, sex, phonenumber, channeltype, intetionbuildid),pageable));
        return ajaxJson;
    }
}