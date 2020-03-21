package com.guoanfamily.palmsale.newhouse.controller;

import com.guoanfamily.palmsale.common.AjaxJson;
import com.guoanfamily.palmsale.common.abstractobj.ApiController;
import com.guoanfamily.palmsale.newhouse.entity.PCCount;
import com.guoanfamily.palmsale.newhouse.model.RecommentCountModel;
import com.guoanfamily.palmsale.newhouse.model.SaleCountGroupModel;
import com.guoanfamily.palmsale.newhouse.model.SaleCountsModel;
import com.guoanfamily.palmsale.newhouse.model.TakerCountsModel;
import com.guoanfamily.palmsale.newhouse.repository.NCustomsallocateRepository;
import com.guoanfamily.palmsale.newhouse.repository.PCCountRepository;
import com.guoanfamily.palmsale.newhouse.service.ObjectDaoServiceI;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/22.
 */
@RestController
@RequestMapping(ApiController.COUNT_URL)
public class CountManage {
    @Resource
    private ObjectDaoServiceI objectDaoService;
    private final PCCountRepository pcCountRepository;
    private final NCustomsallocateRepository nCustRepository;
    private final AjaxJson ajaxJson ;
    public CountManage(PCCountRepository pcCountRepository, NCustomsallocateRepository nCustRepository, AjaxJson ajaxJson) {
        this.pcCountRepository = pcCountRepository;
        this.nCustRepository = nCustRepository;
        this.ajaxJson = ajaxJson;
    }

    /**
     * 代办事项统计
     * @param uid
     * @return
     */

    @RequestMapping(value = "/scheduleList",method = RequestMethod.GET)
    public AjaxJson scheduleList(@RequestParam("uid")String uid){
        try {
            List<PCCount> list =  pcCountRepository.findAll();
            if(list.size()>0){
                PCCount pcCount = list.get(0);
                ajaxJson.setMsg("success").setStatus(200).setData(pcCount);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ajaxJson.setMsg("fail").setStatus(201);
        }
        return ajaxJson;
    }
    /**
     * 每日推客统计
     * @param month
     * @return
     */
    @ApiOperation(value = "每日推客统计", notes = "每日推客统计")
    @RequestMapping(value = "/recommendCount",method = RequestMethod.GET)
    public AjaxJson recommendCount(@RequestParam("month")String month){
        try {
            List<RecommentCountModel> list  =  new ArrayList<>();
            String sql = "SELECT DATE_FORMAT(n.recommendedtime, '%Y-%m-%d') days, COUNT(*) AS num FROM n_customsallocate n WHERE n.recommendedtime LIKE '"+month+"%' GROUP BY days";
            EntityManager entityManager =objectDaoService.getEntityManager();
            Query query= entityManager.createNativeQuery(sql,RecommentCountModel.class);
            list= query.getResultList();
            ajaxJson.setMsg("success").setStatus(200).setData(list);
        } catch (Exception e) {
            e.printStackTrace();
            ajaxJson.setMsg("fail").setStatus(201).setSuccess(false);
        }
        return ajaxJson;
    }

    /**
     * 销售员拓客量统计
     * @param params
     * @return
     */
    @ApiOperation(value = "销售员拓客量统计", notes = "销售员拓客量统计")
    @RequestMapping(value = "/tokerCount",method = RequestMethod.POST)
    public AjaxJson tokerCount(@RequestBody Map<String,String> params){
        String month = params.get("month");
        String uid = params.get("uid");
        try {
            List<TakerCountsModel> list = new ArrayList<>();
            String sql="SELECT  tsb.id as saleid, tsb.realname as salename, count(nc.id) as num, DATE_FORMAT(nc.recommendedtime, '%Y-%m') months FROM t_s_base_user tsb " +
                    " JOIN t_s_role_user tru ON tru.userid = tsb.id " +
                    " JOIN t_s_role tsr ON tsr.ID = tru.roleid AND tsr.rolename = '展示中心销售' " +
                    " left JOIN n_customsallocate  nc on nc.userid=tsb.id and nc.recommendedtime LIKE '"+month+"%' " +
                    " GROUP BY tsb.id";
            EntityManager entityManager =objectDaoService.getEntityManager();
            Query query= entityManager.createNativeQuery(sql,TakerCountsModel.class);
            list= query.getResultList();
            ajaxJson.setMsg("success").setStatus(200).setData(list);
        } catch (Exception e) {
            e.printStackTrace();
            ajaxJson.setMsg("fail").setStatus(201).setSuccess(false);
        }
        return ajaxJson;
    }
    /**
     * 销售套数排行
     * @param params
     * @return
     */
    @ApiOperation(value = "销售套数排行", notes = "销售套数排行")
    @RequestMapping(value = "/saleCount",method = RequestMethod.POST)
    public AjaxJson saleCount(@RequestBody Map<String,String> params){
        String year = params.get("year");
        String uid = params.get("uid");
        try {
            String sql="SELECT tsb.id AS saleid, tsb.realname AS salename, count(cto.id) AS num, DATE_FORMAT(cto.signtime, '%Y') years FROM t_s_base_user tsb " +
                    " JOIN t_s_role_user tru ON tru.userid = tsb.id\n" +
                    " JOIN t_s_role tsr ON tsr.ID = tru.roleid AND tsr.rolename = '展示中心销售'\n" +
                    " LEFT JOIN n_customsallocate  nc ON nc.userid=tsb.id\n" +
                    " LEFT JOIN cust_order cto ON cto.custphonenumber = nc.phonenumber and cto.orderstate='7' AND cto.signtime LIKE '"+year+"%' " +
                    " GROUP BY tsb.id";
            EntityManager entityManager =objectDaoService.getEntityManager();
            Query query= entityManager.createNativeQuery(sql,SaleCountsModel.class);
            List<SaleCountsModel> list= query.getResultList();
            ajaxJson.setMsg("success").setStatus(200).setData(list);
        } catch (Exception e) {
            e.printStackTrace();
            ajaxJson.setMsg("fail").setStatus(201).setSuccess(false);
        }
        return ajaxJson;
    }
    /**
     * 销售分组列表
     * @return
     */
    @ApiOperation(value = "销售分组列表", notes = "销售分组列表")
    @RequestMapping(value = "/saleCountGroupByDepart",method = RequestMethod.GET)
    public AjaxJson saleCountGroupByDepart(@RequestParam("uid") String uid){
        try {
            String sql="SELECT tsd.id AS departid, tsd.departname AS departname, count(nc.id) AS num, DATE_FORMAT(nc.recommendedtime, '%Y-%d') years FROM t_s_base_user tsb " +
                    " JOIN t_s_role_user tru ON tru.userid = tsb.id " +
                    " JOIN t_s_role tsr ON tsr.ID = tru.roleid AND tsr.rolename = '展示中心销售' " +
                    " JOIN t_s_user_org tuo ON tuo.user_id = tsb.id " +
                    " JOIN t_s_depart tsd ON tsd.id = tuo.org_id " +
                    " LEFT JOIN n_customsallocate nc ON nc.userid = tsb.id " +
                    " GROUP BY tsd.id";
            EntityManager entityManager =objectDaoService.getEntityManager();
            Query query= entityManager.createNativeQuery(sql,SaleCountGroupModel.class);
            List<SaleCountGroupModel> list= query.getResultList();
            ajaxJson.setMsg("success").setStatus(200).setData(list);
        } catch (Exception e) {
            e.printStackTrace();
            ajaxJson.setMsg("fail").setStatus(201).setSuccess(false);
        }
        return ajaxJson;
    }

}
