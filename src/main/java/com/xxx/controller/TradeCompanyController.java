package com.xxx.controller;

import com.guoanfamily.palmsale.common.AjaxJson;
import com.guoanfamily.palmsale.common.util.ExcelUtils;
import com.guoanfamily.palmsale.common.abstractobj.ApiController;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import com.github.wenhao.jpa.Specifications;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import org.apache.commons.io.IOUtils;
import com.xxx.entity.TradeCompanyEntity;
import com.xxx.repository.TradeCompanyRepository;


/**
 * trade_company表操作
 *
 * @author 张文旭
 * @version 1.0
 * @date 2017-06-20 14:42:47
 */
@Api(description = "trade_company表操作相关接口")
@RestController
@RequestMapping("/api/v1/partner")
public class TradeCompanyController extends ApiController {

    private static Logger logger = LoggerFactory.getLogger(TradeCompanyController.class);

    @Autowired
    private TradeCompanyRepository tradeCompanyRepository;
    @Autowired
    private AjaxJson ajaxJson;

    /**
     * 列表查询
     */
    @ApiOperation(value = "trade_company表操作列表", notes = "分页显示trade_company表操作")
    @GetMapping("/list")
    public AjaxJson list(@RequestParam(value = "page", defaultValue = "0") Integer page,
                         @RequestParam(value = "size", defaultValue = "2") Integer size,
                         @RequestParam(value = "id", required = false) String id) {
        logger.info("查询列表数据: 页码：" + page + ",每页条数：" + size);
        //排序查询
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        //设置分页查询参数和排序规则
        Pageable pageable = new PageRequest(page, size, sort);
        //加入条件查询，根据bean--字段对应查询，自行修改
        Specification<TradeCompanyEntity> specification = Specifications.<TradeCompanyEntity>and()
                .like(StringUtils.isNotBlank(id), "id", id)
                .build();
        return ajaxJson.setSuccess(true).setStatus(200).setData(tradeCompanyRepository.findAll(specification, pageable));
    }


    /**
     * 详细信息
     */
    @ApiOperation(value = "根据id查询详细信息", notes = "根据id查询详细信息")
    @GetMapping("/info/{id}")
    public AjaxJson info(@PathVariable("id") String id) {
        logger.info("根据id查询详细信息:id "+ id);
        TradeCompanyEntity tradeCompany = tradeCompanyRepository.findOne(id);
        if (null == tradeCompany) {
            return ajaxJson.setSuccess(true).setStatus(200);
        }
        return ajaxJson.setSuccess(true).setStatus(200).setData(tradeCompany);
    }

    /**
     * 保存
     */
    @ApiOperation(value = "新增数据", notes = "新增数据")
    @PostMapping(value = "/save")
    public AjaxJson save(@RequestBody TradeCompanyEntity tradeCompany) {
        logger.info("新增数据");
        tradeCompany =tradeCompanyRepository.save(tradeCompany);
        ajaxJson.setSuccess(true).setStatus(200).setData(tradeCompany);
        return ajaxJson;

    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改数据", notes = "修改数据")
    @PutMapping("/update")
    public AjaxJson update(@RequestBody TradeCompanyEntity tradeCompany) {
        logger.info("修改数据");
        TradeCompanyEntity tradeCompanyInfo = tradeCompanyRepository.findOne(tradeCompany.getId());
        if (null == tradeCompanyInfo) {
            return ajaxJson.setSuccess(false).setStatus(200).setMsg("不存在的信息");
        }
        tradeCompany =tradeCompanyRepository.save(tradeCompany);
        ajaxJson.setSuccess(true).setStatus(200).setData(tradeCompany);
        return ajaxJson;
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除", notes = "刪除")
    @DeleteMapping(value = "{id}")
    public AjaxJson delete(@PathVariable(value = "id") String id) {
        logger.info("删除数据");
        TradeCompanyEntity tradeCompanyInfo = tradeCompanyRepository.findOne(id);
        if (null == tradeCompanyInfo) {
            return ajaxJson.setSuccess(false).setStatus(200).setMsg("不存在的信息");
        }
            tradeCompanyRepository.delete(id);
        return ajaxJson.setSuccess(true).setStatus(200).setMsg("数据删除成功！");
    }

    /**
     * 批量删除
     */
    @ApiOperation(value = "批量删除", notes = "批量删除")
    @DeleteMapping(value = "{ids}")
    public AjaxJson deleteBatch(@PathVariable(value = "ids") String ids) {
        logger.info("批量删除");
        if (ids.indexOf(",") == -1) {
            return ajaxJson.setSuccess(false).setStatus(500).setMsg("错误参数");
        }
        for (String id : ids.split(",")) {
                tradeCompanyRepository.delete(id);
        }

        return ajaxJson.setSuccess(true).setStatus(200).setMsg("数据删除成功！");
    }

    /**
     * Excel导出
     *
     * @param res
     */
    @ApiOperation(value = "Excel导出", notes = "Excel导出")
    @GetMapping(value = "exportExcel")
    public void exportExcel(HttpServletResponse res) {
        InputStream is = null;
        BufferedOutputStream bos = null;
        BufferedInputStream bis = null;
        try {
            String local_file_path = ExcelUtils.buildExcel(tradeCompanyRepository.findAll());
            File file = new File(local_file_path);
            is = new FileInputStream(file);
            String fileName = file.getName();
            res.reset();
            res.setHeader("Content-Disposition", "attachment; filename=\"" + new String(fileName.getBytes("gbk"), "iso-8859-1") + "\"");
            res.addHeader("Content-Length", "" + file.length());
            res.setContentType("application/octet-stream;charset=UTF-8");
            OutputStream os = res.getOutputStream();
            bos = new BufferedOutputStream(os);
            bis = new BufferedInputStream(is);
            int length = 0;
            byte[] temp = new byte[1 * 1024 * 10];
            while ((length = bis.read(temp)) != -1) {
                bos.write(temp, 0, length);
            }
            bos.flush();
        } catch (Exception e) {
            logger.error("下载异常", e);
        } finally {
            IOUtils.closeQuietly(bis);
            IOUtils.closeQuietly(bos);
            IOUtils.closeQuietly(is);
        }

    }

}
