package com.guoanfamily.palmsale.sellHouse.controller;

import com.github.wenhao.jpa.Specifications;
import com.guoanfamily.palmsale.common.AjaxJson;
import com.guoanfamily.palmsale.common.abstractobj.ApiController;
import com.guoanfamily.palmsale.newhouse.service.ObjectDaoServiceI;
import com.guoanfamily.palmsale.sellHouse.entity.Share;
import com.guoanfamily.palmsale.sellHouse.model.ShareModel;
import com.guoanfamily.palmsale.sellHouse.repository.ShareRepository;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(ApiController.Share_URL)
public class ShareController extends ApiController{
    @Resource
    private ObjectDaoServiceI objectDaoService;
    private ShareRepository shareRepository;
    private  AjaxJson ajaxJson;
    @Autowired
    public ShareController(ShareRepository shareRepository , AjaxJson ajaxJson) {
        this.shareRepository = shareRepository;
        this.ajaxJson = ajaxJson;
    }
    /**
     * 业务分享信息汇总查询接口
     * @return AjaxJson
     */
    @ApiOperation(value="业务分享信息列表分頁查", notes="业务分享信息數據")
    @RequestMapping(value = "/statistics", method=RequestMethod.GET)
    public AjaxJson statistics(@RequestParam(value = "businessid", defaultValue = "0") String businessid) {
        List<ShareModel> list  =  new ArrayList<>();
        String sql = "SELECT  '分享数量（个）'as  id,count(u.id) as sharesum,sum(CASE WHEN u.sharetype = 0 THEN 1 ELSE 0 END ) AS sharecommon, sum( CASE WHEN u.sharetype = 1 THEN 1 ELSE 0 END ) AS  shareall, sum( CASE WHEN u.sharetype = 2 THEN 1 ELSE 0 END ) AS trade FROM p_share u where  u.businessid="+"'"+businessid+"'";
        EntityManager entityManager =objectDaoService.getEntityManager();
        Query query= entityManager.createNativeQuery(sql,ShareModel.class);
        list= query.getResultList();
        Map<String,Object> map = new LinkedHashMap<>();
        map.put("content",list);
        ajaxJson.setSuccess(true).setStatus(200).setData(map);
        return ajaxJson;
    }
    /**
     * 业务分享信息分页查询接口
     * @param page
     * @param size
     * @return AjaxJson
     */
    @ApiOperation(value="业务分享信息列表分頁查", notes="业务分享信息數據")
    @RequestMapping(value = "/findPage", method=RequestMethod.GET)
    public AjaxJson getEntryByPageable(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                       @RequestParam(value = "size", defaultValue = "10") Integer size,
                                       @RequestParam(value = "businessid", defaultValue = "0") String businessid,
                                       @RequestParam(value = "sharetype", defaultValue = "0") String sharetype) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(page, size, sort);
        Specification<Share> specification = Specifications.<Share>and()
        .eq(StringUtils.isNotBlank(businessid),"businessid" , businessid)
        .eq(StringUtils.isNotBlank(sharetype),"sharetype" , sharetype)
        .build();
        ajaxJson.setSuccess(true).setStatus(200).setData(shareRepository.findAll(specification,pageable));
        return ajaxJson;
    }

    /**
     *  业务分享信息新增接口
     * @param share
     * @return AjaxJson
     */
    @ApiOperation(value="业务分享信息save接口", notes="业务分享信息")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public AjaxJson save(@RequestBody Share share) {
        share = shareRepository.save(share);
        ajaxJson.setSuccess(true).setStatus(200).setData(share);
        return ajaxJson;
    }
}
