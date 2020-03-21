package com.guoanfamily.palmsale.sellHouse.controller;

import com.guoanfamily.palmsale.common.AjaxJson;
import com.guoanfamily.palmsale.common.abstractobj.ApiController;
import com.guoanfamily.palmsale.sellHouse.entity.CustomerDealInfo;
import com.guoanfamily.palmsale.sellHouse.repository.CustomerDealInfoRepository;
import com.guoanfamily.palmsale.sellHouse.service.CustomerDealInfoService;
import com.guoanfamily.palmsale.sellHouse.service.CustomerDealInfoServiceImp;
import com.guoanfamily.palmsale.sellTool.controller.KnowLedgeController;
import com.guoanfamily.palmsale.sellTool.entity.KnowLedgeBrowse;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/10.
 */
@RestController
@RequestMapping(ApiController.CUSTOMERDEALINFO_URL)
public class CustomerDealInfoController extends ApiController {

    private CustomerDealInfoRepository customerDealInfoRepository;
    private AjaxJson ajaxJson;
    private CustomerDealInfoService custDealInfoSe;
    private CustomerDealInfoServiceImp customerDealInfoServiceImp;

    @Autowired
    public CustomerDealInfoController(CustomerDealInfoServiceImp customerDealInfoServiceImp,CustomerDealInfoService custDealInfoSe, CustomerDealInfoRepository customerDealInfoRepository, AjaxJson ajaxJson) {
        this.customerDealInfoRepository = customerDealInfoRepository;
        this.ajaxJson = ajaxJson;
        this.custDealInfoSe = custDealInfoSe;
        this.customerDealInfoServiceImp = customerDealInfoServiceImp;
    }

    @ApiOperation(value = "客户修改信息", notes = "客户成交管理信息")
    @RequestMapping(value = "/saveCustDealInfo", method = RequestMethod.POST)
    public AjaxJson saveCustDealInfo(@RequestBody CustomerDealInfo customerDealInfo) {
        if (customerDealInfo.getId() == null || customerDealInfo.getId().equals("")){
            customerDealInfo.setCreatetime(KnowLedgeController.getCurrentTime());
            customerDealInfo.setUpdatetime(KnowLedgeController.getCurrentTime());
            customerDealInfo.setDealdate(KnowLedgeController.getCurrentTime());
            customerDealInfo.setPaymoneystatus(0);
        }else {
            customerDealInfo.setUpdatetime(KnowLedgeController.getCurrentTime());
        }
        customerDealInfo = customerDealInfoServiceImp.customerDealInfoSave(customerDealInfo);
        ajaxJson.setSuccess(true).setStatus(200).setData(customerDealInfo);
        return ajaxJson;
    }

    /**
     *
     * @param customerid
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "web后台成交管理ciutomerid查询", notes = "客户成交管理信息")
    @RequestMapping(value = "/findPage")
    public AjaxJson getCusdeal(@RequestParam(value="customerid") String customerid,@RequestParam(value = "page", defaultValue = "0") Integer page,
                               @RequestParam(value = "size", defaultValue = "10")Integer size ){
        List<CustomerDealInfo> customerDealInfoList = new ArrayList<>();
        if(customerid != null && ! customerid.equals("")){
            Sort sort = new Sort(Sort.Direction.DESC, "id");
            Pageable pageable = new PageRequest(page, size, sort);
            customerDealInfoList = customerDealInfoRepository.getCustomSalloCate(customerid);
        }
        return ajaxJson.setStatus(200).setSuccess(true).setData(customerDealInfoList);
    }

    /**
     *
     * @param id
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "成交id条件的查询", notes = "客户成交管理信息")
    @RequestMapping(value = "/dealinfo/findId", method= RequestMethod.GET)
    public AjaxJson findId(@RequestParam String id, @RequestParam(value = "page", defaultValue = "0") Integer page,
                           @RequestParam(value = "size", defaultValue = "10") Integer size){
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        org.springframework.data.domain.Pageable pageable = new PageRequest(page, size, sort);
        Page<CustomerDealInfo> hotartShareList = customerDealInfoRepository.findAll(custDealInfoSe.where(id),pageable);
        return ajaxJson.setSuccess(true).setStatus(200).setData(hotartShareList);
    }

}
