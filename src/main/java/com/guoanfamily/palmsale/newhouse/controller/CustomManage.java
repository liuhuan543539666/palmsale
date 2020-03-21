package com.guoanfamily.palmsale.newhouse.controller;

import com.guoanfamily.palmsale.common.AjaxJson;
import com.guoanfamily.palmsale.common.abstractobj.ApiController;
import com.guoanfamily.palmsale.newhouse.Utils.SqlUtils;
import com.guoanfamily.palmsale.newhouse.entity.*;
import com.guoanfamily.palmsale.newhouse.model.VCustOrderInfo;
import com.guoanfamily.palmsale.newhouse.repository.*;
import com.guoanfamily.palmsale.newhouse.service.ObjectDaoServiceI;
import com.guoanfamily.palmsale.newhouse.service.VCustListServiceI;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 客户管理
 * Created by Administrator on 2017/5/16.
 */
@RestController
@RequestMapping(ApiController.CUSTMANAGER_URL)
public class CustomManage {
private final VCustListRepository vCustListRepository;
private final VVistitHistoryRepository vVistitHistoryRepository;
private final VFollowHistoryRepository vFollowHistoryRepository;
private final BookScanBuildingRepository bookScanBuildingRepository;
private final NCustomsallocateRepository nocateRepository;
private final VCustDetailRepository vcustdetailRepository;
private final AAgentCustomdetailRepository AgentCustomdetailRepository;
private final NCustomsallocateRepository nCARepository;
private final AjaxJson ajaxJson ;
@Resource
private final ObjectDaoServiceI objectDaoService;
private final VCustListServiceI vCustListService;
    @Autowired
    public CustomManage(VCustListRepository vCustListRepository, VVistitHistoryRepository vVistitHistoryRepository, VFollowHistoryRepository vFollowHistoryRepository, BookScanBuildingRepository bookScanBuildingRepository, NCustomsallocateRepository nocateRepository, VCustDetailRepository vcustdetailRepository, NCustomsallocateRepository caRepository, AAgentCustomdetailRepository agentCustomdetailRepository, NCustomsallocateRepository nCARepository, AjaxJson ajaxJson, ObjectDaoServiceI objectDaoService, VCustListServiceI vCustListService) {
        this.vCustListRepository = vCustListRepository;
        this.vVistitHistoryRepository = vVistitHistoryRepository;
        this.vFollowHistoryRepository = vFollowHistoryRepository;
        this.bookScanBuildingRepository = bookScanBuildingRepository;
        this.nocateRepository = nocateRepository;
        this.vcustdetailRepository = vcustdetailRepository;
        AgentCustomdetailRepository = agentCustomdetailRepository;
        this.nCARepository = nCARepository;
        this.ajaxJson = ajaxJson;
        this.objectDaoService = objectDaoService;
        this.vCustListService = vCustListService;
    }
    /*客户列表查询*/
    @ApiOperation(value = "客户查询信息", notes = "客户列表查询")
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public AjaxJson list(@RequestBody Map<String,String> params){
        String keywords = params.get("keywords");//手机号or姓名
        String custstate = params.get("custstate");
        String channeltype = params.get("channeltype");
        String company = params.get("company");
        String buildname = params.get("buildname");
        String salename = params.get("salename");
        String createdate = params.get("createdate");
        String page = params.get("page");

        VCustList model = new VCustList();
        if(isMobileNO(keywords)){
            model.setPhonenumber(keywords);
        }else{
            model.setCustname(keywords);
        }
        if("已分配".equals(custstate)){
            model.setNuastateid(custstate);
        }
        model.setChanneltype(channeltype);
        model.setCmpname(company);
        model.setRealname(salename);
        model.setBuildname(buildname);
        model.setBetweentime(createdate);
        model.setPage(page);
        Page<VCustList> list = null;
        try {
            list = vCustListService.findAll(model);
            ajaxJson.setStatus(200).setMsg("操作成功").setSuccess(true).setData(list);
        } catch (Exception e) {
            e.printStackTrace();
            ajaxJson.setStatus(201).setMsg("返回失败").setSuccess(false);
        }
        return ajaxJson;
    }

    //带看记录查询
    @ApiOperation(value = "带看记录查询信息", notes = "带看记录查询")
    @RequestMapping(value = "/visitHistory",method = RequestMethod.GET)
    public AjaxJson visitHistory(@RequestParam("phonenumber") String phonenumber){
        try {
            List<V_visit_history> list = new ArrayList<>();
            list = vVistitHistoryRepository.findAllByPhonenumber(phonenumber);
            ajaxJson.setSuccess(true).setStatus(200).setMsg("success").setData(list);
        } catch (Exception e) {
            e.printStackTrace();
            ajaxJson.setSuccess(false).setStatus(201).setMsg("fail");
        }
        return ajaxJson;
    }

    //客户跟进记录查询
    @ApiOperation(value = "客户跟进记录查询信息", notes = "客户跟进记录查询")
    @RequestMapping(value = "/followHistory",method = RequestMethod.GET)
    public  AjaxJson followHistory(@RequestParam("phonenumber")String phonenumber){
        try {
            List<V_follow_history> list = new ArrayList<>();
            list = vFollowHistoryRepository.findAllByPhonenumber(phonenumber);
            ajaxJson.setSuccess(true).setStatus(200).setMsg("success").setData(list);
        } catch (Exception e) {
            e.printStackTrace();
            ajaxJson.setSuccess(false).setStatus(201).setMsg("fail");
        }
        return  ajaxJson;
    }

    //客户确认带看
    @ApiOperation(value = "客户确认带看", notes = "客户确认带看")
    @RequestMapping(value = "/confirmVisit",method = RequestMethod.POST)
    public  AjaxJson confirmVisit(@RequestBody Map<String, String> params){
        String phone = params.get("phone");//手机号
        String channeltype = params.get("channeltype");//带看渠道
        String actvisiter = params.get("visiter");//实际带看人
        String actcustomname = params.get("visitname");//实际到访客户姓名
        String actvistitcount = params.get("visitcount");//实际到访及陪同人数
        String visittime = params.get("visittime");//实际到访时间
        String remark = params.get("remark");//备注信息
        try {
            Date time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(visittime);
            bookScanBuildingRepository.updateBookscnbuildByPhonenum(channeltype,actvisiter,actcustomname,actvistitcount,time,remark,"1",phone);
            ajaxJson.setSuccess(true).setStatus(200).setMsg("success");
        } catch (Exception e) {
            e.printStackTrace();
            ajaxJson.setSuccess(false).setStatus(201).setMsg("fail");
        }
        return  ajaxJson;
    }

    //客户分配功能
    @ApiOperation(value = "客户分配功能", notes = "客户分配功能")
    @RequestMapping(value = "/custAllot",method = RequestMethod.POST)
    public AjaxJson custAllot(@RequestBody Map<String,String> params){
        String sid = params.get("sid");//销售id
        String cid = params.get("cid");//客户id
        try {
            nocateRepository.updateNCustomsallocateById(sid,cid);
            ajaxJson.setSuccess(true).setStatus(200).setMsg("success");
        } catch (Exception e) {
            e.printStackTrace();
            ajaxJson.setSuccess(false).setStatus(201).setMsg("fail");
        }
        return ajaxJson;
    }

    //客户详情展示
    @ApiOperation(value = "客户详情展示", notes = "客户详情展示")
    @RequestMapping(value = "/custDetail",method = RequestMethod.GET)
    public AjaxJson custDetail(@RequestParam("phonenumber") String phonenumber){
        try {
            String sql= SqlUtils.CUST_DETAIL_SQL;
            String sql2= SqlUtils.CUST_ORDER_INFO_SQL;
            sql= ContactSql(sql,phonenumber);
            sql2= ContactSql(sql2,phonenumber);
            EntityManager entityManager =objectDaoService.getEntityManager();
            Query query= entityManager.createNativeQuery(sql,VCustdetail.class);
            Query query2= entityManager.createNativeQuery(sql2,VCustOrderInfo.class);
            Map<String,Object> map = new HashMap<>();
            List<VCustdetail> list = query.getResultList();
            List<VCustOrderInfo> list2 = query2.getResultList();
            if(list.size()>0){
                map.put("cust",list.get(0));
            }
            if(list2.size()>0){
                map.put("order",list2.get(0));
            }
            ajaxJson.setSuccess(true).setStatus(200).setMsg("success").setData(map);
        } catch (Exception e) {
            e.printStackTrace();
            ajaxJson.setSuccess(false).setStatus(201).setMsg("fail");
        }
        return ajaxJson;
    }

    //编辑客户信息
    @ApiOperation(value = "客户信息", notes = "编辑客户信息")
    @RequestMapping(value = "/detailedit",method = RequestMethod.POST)
    public AjaxJson detailedit(@RequestBody VCustdetail req){
        try {

            NCustomsallocate nCustomsallocate =  nCARepository.findByPhonenumber(req.getPhonenumber());
            A_agentcustomdetail aAgentcustomdetail = AgentCustomdetailRepository.findByPhonenumber(req.getPhonenumber());
            boolean isTrue = vCustListService.editEntity(req,nCustomsallocate,aAgentcustomdetail);
            if(isTrue){
                ajaxJson.setStatus(200).setMsg("操作成功").setSuccess(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ajaxJson.setStatus(201).setMsg("操作失败").setSuccess(false);
        }
        return ajaxJson;
    }
    //组装sql
    public String ContactSql(String sql,String phonenumber){
        sql = sql.replace("15084569652",phonenumber);
        return sql;
    }
    //校验手机号码
    public boolean isMobileNO(String mobiles) {
        Pattern p = Pattern.compile("^(13[0-9]|14(5|7)|15[0-9]|17[0-9]|18[0-9])\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }
}
