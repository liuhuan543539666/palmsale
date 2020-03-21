package com.guoanfamily.palmsale.sellHouse.controller;

import com.github.wenhao.jpa.Specifications;
import com.guoanfamily.palmsale.common.AjaxJson;
import com.guoanfamily.palmsale.common.abstractobj.ApiController;
import com.guoanfamily.palmsale.newhouse.service.ObjectDaoServiceI;
import com.guoanfamily.palmsale.sellHouse.entity.Browse;
import com.guoanfamily.palmsale.sellHouse.model.BrowseModel;
import com.guoanfamily.palmsale.sellHouse.repository.BrowseRepository;
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
@RequestMapping(ApiController.Brower_URL)
public class BrowseController extends ApiController{
    @Resource
    private ObjectDaoServiceI objectDaoService;
    private BrowseRepository browseRepository;
    private  AjaxJson ajaxJson;
    @Autowired
    public BrowseController(BrowseRepository browseRepository, AjaxJson ajaxJson) {
        this.browseRepository = browseRepository;
        this.ajaxJson = ajaxJson;
    }
    /**
     * 业务浏览信息汇总查询接口
     * @return AjaxJson
     */
    @ApiOperation(value="业务浏览信息列表分頁查", notes="业务浏览信息數據")
    @RequestMapping(value = "/statistics", method=RequestMethod.GET)
    public AjaxJson statistics(@RequestParam(value = "businessid", defaultValue = "0") String businessid) {
        List<BrowseModel> list  =  new ArrayList<>();
        String sql = "SELECT  '浏览量（个）'as  id,count(u.id) as sum,sum( CASE WHEN u.browsetype = 0 THEN 1 ELSE 0 END ) AS consultant, sum( CASE WHEN u.browsetype = 1 THEN 1 ELSE 0 END ) AS  other FROM p_browse u where  u.businessid="+"'"+businessid+"'";
        EntityManager entityManager =objectDaoService.getEntityManager();
        Query query= entityManager.createNativeQuery(sql,BrowseModel.class);
        list= query.getResultList();
        Map<String,Object> map = new LinkedHashMap<>();
        map.put("content",list);
        ajaxJson.setSuccess(true).setStatus(200).setData(map);
        return ajaxJson;
    }

    /**
     * 业务浏览信息分页查询接口
     * @param page
     * @param size
     * @return AjaxJson
     */
    @ApiOperation(value="业务浏览信息列表分頁查", notes="业务浏览信息數據")
    @RequestMapping(value = "/findPage", method=RequestMethod.GET)
    public AjaxJson findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
                              @RequestParam(value = "size", defaultValue = "10") Integer size,
                              @RequestParam(value = "businessid", defaultValue = "0") String businessid,
                              @RequestParam(value = "browsetype", defaultValue = "0") String browsetype) {
            Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(page, size, sort);
        Specification<Browse> specification = Specifications.<Browse>and()
        .like(StringUtils.isNotBlank(businessid),"businessid" , businessid)
        .like(StringUtils.isNotBlank(browsetype),"browsetype" , browsetype)
        .build();
        ajaxJson.setSuccess(true).setStatus(200).setData(browseRepository.findAll(specification,pageable));
        return ajaxJson;
    }

    /**
     *  业务浏览信息新增接口
     * @param browse
     * @return AjaxJson
     */
    @ApiOperation(value="业务浏览信息save接口", notes="业务浏览信息")
    @RequestMapping(value = "/saveBrowse", method = RequestMethod.POST)
    public AjaxJson save(@RequestBody Browse browse) {
        browse = browseRepository.save(browse);
        ajaxJson.setSuccess(true).setStatus(200).setData(browse);
        return ajaxJson;
    }


}
