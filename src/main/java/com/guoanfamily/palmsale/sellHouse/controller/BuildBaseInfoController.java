package com.guoanfamily.palmsale.sellHouse.controller;

import com.github.wenhao.jpa.Specifications;
import com.guoanfamily.palmsale.common.AjaxJson;
import com.guoanfamily.palmsale.common.abstractobj.ApiController;
import com.guoanfamily.palmsale.newhouse.service.ObjectDaoServiceI;
import com.guoanfamily.palmsale.sellHouse.entity.Buildbaseinfo;
import com.guoanfamily.palmsale.sellHouse.entity.BuildbaseinfoDetail;
import com.guoanfamily.palmsale.sellHouse.entity.Initiationinfo;
import com.guoanfamily.palmsale.sellHouse.model.BuildbaseinfoModel;
import com.guoanfamily.palmsale.sellHouse.repository.BuildBaseInfoDetailRepository;
import com.guoanfamily.palmsale.sellHouse.repository.BuildBaseInfoRepository;
import com.guoanfamily.palmsale.sellHouse.repository.CustomsInitiationRepository;
import com.guoanfamily.palmsale.sellHouse.service.CustomsInitiationService;
import com.guoanfamily.palmsale.system.entity.User;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.*;

@RestController
@RequestMapping(ApiController.Build_URL)
public class BuildBaseInfoController extends ApiController{
    @Resource
    private ObjectDaoServiceI objectDaoService;
    private  BuildBaseInfoRepository buildBaseInfoRepository;
    private  BuildBaseInfoDetailRepository buildBaseInfoDetailRepository;
    private  AjaxJson ajaxJson;
    @Autowired
    public BuildBaseInfoController(BuildBaseInfoRepository buildBaseInfoRepository,BuildBaseInfoDetailRepository buildBaseInfoDetailRepository, AjaxJson ajaxJson) {
        this.buildBaseInfoRepository = buildBaseInfoRepository;
        this.buildBaseInfoDetailRepository=buildBaseInfoDetailRepository;
        this.ajaxJson = ajaxJson;
    }

    /**
     * 楼盘信息分页查询接口
     * @param page
     * @param size
     * @return AjaxJson
     */
    @ApiOperation(value="楼盘信息列表分頁查", notes="楼盘信息數據")
    @RequestMapping(value = "/findPage", method=RequestMethod.GET)
    public AjaxJson getEntryByPageable(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                      @RequestParam(value = "size", defaultValue = "10") Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(page, size, sort);
        ajaxJson.setSuccess(true).setStatus(200).setData(buildBaseInfoRepository.findAll(pageable));
        return ajaxJson;
    }
    /**
     * 后台楼盘信息分页查询接口
     * @param page
     * @param size
     * @return AjaxJson
     */
    @ApiOperation(value="后台楼盘信息列表分頁查", notes="后台楼盘信息數據")
    @RequestMapping(value = "/findBuildBaseInfoPage", method=RequestMethod.GET)
    public AjaxJson findBuildBaseInfoPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                           @RequestParam(value = "size", defaultValue = "10") Integer size,
                                           @RequestParam String province,
                                           @RequestParam String buildname,
                                           @RequestParam String onsalestatus,
                                           @RequestParam String deadlinestatus,
                                           @RequestParam String updown,
                                           @RequestParam(value = "browseStart", defaultValue = "") String browseStart,
                                           @RequestParam String browseEnd
                                          ) {
        String sql = "select * from  (SELECT b.id AS bid,b.pid,b.ad,b.deadlinestatus,b.housetype, b.onsalestatus, b.phone,b.presale_permit, b.updown, b.updowntime, a.*, ( SELECT count(id) FROM p_browse k WHERE\ta.id = k.businessid ) AS browsecount, ( SELECT\tcount(id) FROM p_share k\tWHERE a.id = k.businessid ) AS sharecount FROM b_buildbaseinfo a LEFT JOIN b_buildbaseinfo_detail b ON a.id = b.pid ) k  where 1=1";
        if(StringUtils.isNoneBlank(province)){
            sql+=" and province = "+ "'" +province + "'";
        }
        if(StringUtils.isNoneBlank(buildname)){
            sql+=" and buildname = "+  "'" + buildname + "'";
        }
        if(StringUtils.isNoneBlank(onsalestatus)){
            sql+=" and onsalestatus = "+ onsalestatus;
        }
        if(StringUtils.isNoneBlank(deadlinestatus)){
            sql+=" and deadlinestatus = "+ deadlinestatus;
        }
        if(StringUtils.isNoneBlank(updown)){
            sql+=" and updown = "+ updown;
        }
        if(StringUtils.isNoneBlank(browseStart)){
            sql+=" and browsecount > "+ browseStart;
        }
        if(StringUtils.isNoneBlank(browseEnd)){
            sql+=" and browsecount < "+ browseEnd;
        }
        EntityManager entityManager =objectDaoService.getEntityManager();
        Query query= entityManager.createNativeQuery(sql,BuildbaseinfoModel.class);
        query.setFirstResult(page);
        query.setMaxResults(size);
        List<BuildbaseinfoModel> list  =  new ArrayList<BuildbaseinfoModel>();
        list= query.getResultList();
        Map<String,Object> map = new LinkedHashMap<>();
        map.put("content",list);
        ajaxJson.setSuccess(true).setStatus(200).setData(map);
        return ajaxJson;
    }

    /**
     *  楼盘信息新增接口
     * @param buildbaseinfoModel
     * @return AjaxJson
     */
    @ApiOperation(value="楼盘信息save接口", notes="楼盘信息")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public AjaxJson save(@RequestBody BuildbaseinfoModel buildbaseinfoModel) {

        Buildbaseinfo buildbaseinfo = new Buildbaseinfo();
        BuildbaseinfoDetail buildbaseinfoDetail=new BuildbaseinfoDetail();
        BeanUtils.copyProperties(buildbaseinfoModel,buildbaseinfo);
        BeanUtils.copyProperties(buildbaseinfoModel,buildbaseinfoDetail);
        if(StringUtils.isBlank(buildbaseinfo.getId())){
            buildbaseinfo = buildBaseInfoRepository.save(buildbaseinfo);
            ajaxJson.setSuccess(true).setStatus(200).setData(buildbaseinfo);
        }
        if(StringUtils.isNotBlank(buildbaseinfoModel.getBid())){
            buildbaseinfoDetail.setId(buildbaseinfoModel.getBid());
            buildbaseinfoDetail.setUpdowntime(new Date());
        }

            buildbaseinfoDetail = buildBaseInfoDetailRepository.save(buildbaseinfoDetail);
            ajaxJson.setSuccess(true).setStatus(200).setData(buildbaseinfoDetail);

        return ajaxJson;
    }
}
