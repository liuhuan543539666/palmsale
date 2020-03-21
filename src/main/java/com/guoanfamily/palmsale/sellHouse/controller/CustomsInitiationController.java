package com.guoanfamily.palmsale.sellHouse.controller;

import com.guoanfamily.palmsale.common.AjaxJson;
import com.guoanfamily.palmsale.common.abstractobj.ApiController;
import com.guoanfamily.palmsale.sellHouse.entity.CustOrder;
import com.guoanfamily.palmsale.sellHouse.entity.Initiationinfo;
import com.guoanfamily.palmsale.sellHouse.entity.InvitedCustInfo;
import com.guoanfamily.palmsale.sellHouse.entity.R_Initiation_Buildbase;
import com.guoanfamily.palmsale.sellHouse.mode.PayOrderResult;
import com.guoanfamily.palmsale.sellHouse.repository.CustOrderRepository;
import com.guoanfamily.palmsale.sellHouse.repository.CustomsInitiationRepository;
import com.guoanfamily.palmsale.sellHouse.service.BookscanbuildingServiceI;
import com.guoanfamily.palmsale.sellHouse.service.CustomsInitiationService;
import com.guoanfamily.palmsale.sellHouse.service.CustomsOrderService;
import com.thoughtworks.xstream.XStream;
import io.swagger.annotations.ApiOperation;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(ApiController.CUST_URL)
public class CustomsInitiationController extends ApiController{

    private final CustomsInitiationRepository customsInitiationRepository;
    private AjaxJson ajaxJson;
    private final CustomsInitiationService customsInitiationService;
    private final CustOrderRepository custOrderRepository;
    private final CustomsOrderService customsInvitedService;
    private final BookscanbuildingServiceI bookscanbuildingService;
    @Autowired
    public CustomsInitiationController(BookscanbuildingServiceI bookscanbuildingService, InvitedCustInfoRepository invitedCustInfoRepository,CustomsOrderService customsInvitedService, CustomsInitiationRepository customsInitiationRepository, AjaxJson ajaxJson, CustomsInitiationService customsInitiationService, CustomsInitAndBuildRepository customsInitAndBuildRepository, CustOrderRepository custOrderRepository) {
        this.customsInitiationRepository = customsInitiationRepository;
        this.ajaxJson = ajaxJson;
        this.customsInitiationService = customsInitiationService;
        this.customsInitAndBuildRepository = customsInitAndBuildRepository;
        this.custOrderRepository = custOrderRepository;
        this.customsInvitedService = customsInvitedService;
        this.invitedCustInfoRepository = invitedCustInfoRepository;
        this.bookscanbuildingService = bookscanbuildingService;

    }

    /**
     *  定金入会规则信息新增接口
     * @param partnerInfo
     * @return AjaxJson
     */
    @ApiOperation(value="定金入会规则信息save接口", notes="定金入会规则信息")
    @RequestMapping(value = "/initiation/save", method = RequestMethod.POST)
    public AjaxJson save(@RequestBody Initiationinfo partnerInfo) {
        partnerInfo = customsInitiationRepository.save(partnerInfo);
        ajaxJson.setSuccess(true).setStatus(200).setData(partnerInfo);
        return ajaxJson;
    }

    /**
     * 定金入会规则信息分页查询接口
     * @param page
     * @param size
     * @return AjaxJson
     */
    @ApiOperation(value="定金入会规则信息列表分頁查", notes="分頁顯示定金入会规则信息數據")
    @RequestMapping(value = "/initiation/findPage", method=RequestMethod.GET)
    public AjaxJson getEntryByPageable(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                      @RequestParam(value = "size", defaultValue = "10") Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(page, size, sort);
        ajaxJson.setSuccess(true).setStatus(200).setData(customsInitiationRepository.findAll(pageable));
        return ajaxJson;
    }

    /**
     * 定金入会信息详细记录查询接口
     * @param initiation_name
     * @param buildname
     * @param mincount
     * @param maxcount
     * @param status
     * @param startDate
     * @param endDate
     * @param page
     * @param size
     * @return
     * @throws JSONException
     */
    @ApiOperation(value="定金入会信息详细记录查询", notes="定金入会信息详细记录查询")
    @RequestMapping(value = "/initiation/search", method=RequestMethod.GET)
    public AjaxJson followUpSearch(@RequestParam String initiation_name, @RequestParam String buildname, @RequestParam String mincount, @RequestParam String maxcount, @RequestParam String status, @RequestParam String startDate, @RequestParam String endDate,
                                   @RequestParam(value = "page", defaultValue = "0") Integer page,
                                   @RequestParam(value = "size", defaultValue = "10") Integer size) throws JSONException {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(page, size, sort);
        ajaxJson.setSuccess(true).setStatus(200).setData(customsInitiationRepository.findAll(customsInitiationService.where(initiation_name, buildname, mincount, maxcount, status, startDate, endDate),pageable));
        return ajaxJson;
    }

    /**
     *  定金入会规则与楼盘信息级联新增接口
     * @param partnerInfo
     * @return AjaxJson
     */
    @ApiOperation(value="定金入会规则与楼盘信息级联save接口", notes="定金入会规则与楼盘信息级联新增")
    @RequestMapping(value = "/initiation/saveInitAndBuild", method = RequestMethod.POST)
    public AjaxJson saveInitAndBuild(@RequestBody R_Initiation_Buildbase partnerInfo) {
        partnerInfo = customsInitAndBuildRepository.save(partnerInfo);
        ajaxJson.setSuccess(true).setStatus(200).setData(partnerInfo);
        return ajaxJson;
    }

    /**
     * 入会客户信息列表分頁查询接口
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value="入会客户信息列表分頁查询", notes="入会客户信息信息數據")
    @RequestMapping(value = "/initiation/findPageCust", method=RequestMethod.GET)
    public AjaxJson findPageCust(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                       @RequestParam(value = "size", defaultValue = "10") Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(page, size, sort);
        ajaxJson.setSuccess(true).setStatus(200).setData(custOrderRepository.findAll(pageable));
        return ajaxJson;
    }

    /**
     * 入会客户信息动态查询接口
     * @param custname
     * @param idcard
     * @param phonenumber
     * @param agentid
     * @param mincount
     * @param maxcount
     * @param status
     * @param code
     * @param page
     * @param size
     * @return
     * @throws JSONException
     */
    @ApiOperation(value="入会客户信息动态查询接口", notes="入会客户信息动态查询接口")
    @RequestMapping(value = "/initiation/searchCust", method=RequestMethod.GET)
    public AjaxJson searchCust(@RequestParam String custname, @RequestParam String idcard, @RequestParam String phonenumber, @RequestParam String agentid, @RequestParam String mincount, @RequestParam String maxcount,@RequestParam String status, @RequestParam String code,
                                   @RequestParam(value = "page", defaultValue = "0") Integer page,
                                   @RequestParam(value = "size", defaultValue = "10") Integer size) throws JSONException {
        Sort sort = new Sort(Sort.Direction.DESC, "createtime");
        Pageable pageable = new PageRequest(page, size, sort);
        ajaxJson.setSuccess(true).setStatus(200).setData(custOrderRepository.findAll(customsInvitedService.where(custname,idcard, phonenumber, agentid, mincount, maxcount, status, code),pageable));
        return ajaxJson;
    }

    /**
     *  客户入会信息新增接口
     * @param partnerInfo
     * @return AjaxJson
     */
    @ApiOperation(value="客户入会信息save接口", notes="客户入会信息新增")
    @RequestMapping(value = "/initiation/saveInitCust", method = RequestMethod.POST)
    public AjaxJson saveCust(@RequestBody CustOrder partnerInfo) {
        //存储认购时间
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        partnerInfo.setCreatetime(java.sql.Timestamp.valueOf(String.valueOf(df.format(new Date()))));
        partnerInfo = custOrderRepository.save(partnerInfo);
        ajaxJson.setSuccess(true).setStatus(200).setData(partnerInfo);
        return ajaxJson;
    }

    /**
     *  被邀约人信息新增接口
     * @param partnerInfo
     * @return AjaxJson
     */
    @ApiOperation(value="被邀约人信息save接口", notes="被邀约人信息新增")
    @RequestMapping(value = "/initiation/saveInvitedCust", method = RequestMethod.POST)
    public AjaxJson saveInvitedCust(@RequestBody InvitedCustInfo partnerInfo) {
        partnerInfo = invitedCustInfoRepository.save(partnerInfo);
        ajaxJson.setSuccess(true).setStatus(200).setData(partnerInfo);
        return ajaxJson;
    }

    /**
     *  客户入会成功修改状态接口
     * @param id
     * @return AjaxJson
     */
    @ApiOperation(value="客户入会成功修改状态接口", notes="客户入会成功修改状态")
    @RequestMapping(value = "/initiation/updatestate", method = RequestMethod.GET)
    public AjaxJson updatestate(@RequestParam String id) {
            CustOrder custorder = custOrderRepository.findById(id);
        //存储创建时间
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            custorder.setSigntime(java.sql.Timestamp.valueOf(String.valueOf(df.format(new Date()))));
            custorder.setOrderstate("2");
            custOrderRepository.save(custorder);
            ajaxJson.setSuccess(true).setStatus(200).setData(custorder);
            return ajaxJson;
    }

    /**
     *  客户入会成功查询状态接口
     * @param custphonenumber
     * @param houseresourceid
     * @return AjaxJson
     */
    @ApiOperation(value="客户入会成功查询状态接口", notes="客户入会成功查询状态")
    @RequestMapping(value = "/initiation/findby", method = RequestMethod.GET)
    public AjaxJson findbyid(@RequestParam String custphonenumber, @RequestParam String houseresourceid) {
        CustOrder custorder = custOrderRepository.findByCustphonenumberAndHouseresourceid(custphonenumber, houseresourceid);
        ajaxJson.setSuccess(true).setStatus(200).setData(custorder);
        return ajaxJson;
    }

    /**
     * 微信支付结果回调接口---新
     */
    @RequestMapping(value = "/doReturnForWeiXin",method = RequestMethod.POST,consumes = MediaType.TEXT_XML_VALUE)
    public void  doReturnForWeiXin(HttpServletRequest req,HttpServletResponse resp,@RequestBody String dd){
        XStream xstream = new XStream();
        xstream.processAnnotations(PayOrderResult.class);
        PayOrderResult result = (PayOrderResult)xstream.fromXML(dd);

        System.out.println(result.toString());
        System.out.println("微信支付结果回调接口---新·········");
        try {
            bookscanbuildingService.doReturnForWeiXin(req,resp,result);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }
}
