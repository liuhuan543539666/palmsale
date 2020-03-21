package com.guoanfamily.palmsale.excel;

import com.guoanfamily.palmsale.bean.BeanTes;
import com.guoanfamily.palmsale.common.util.ExcelUtils;
import com.guoanfamily.palmsale.xx.entity.TradeCompanyEntity;
import com.guoanfamily.palmsale.xx.repository.TradeCompanyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * <p>
 * 一句话描述你写的是什么玩意？
 * </p>
 *
 * @auth: 张文旭
 * @time: 2017/6/19 13:39
 * @version: 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ExcelTest {
    private static Logger logger = LoggerFactory.getLogger(BeanTes.class);
    @Autowired
    TradeCompanyRepository tradeCompanyRepository;

    @Test
    public void testExcel() {
        List<TradeCompanyEntity> data = tradeCompanyRepository.findAll();
        logger.debug("数据：" + data);
        logger.debug("生成excel" + ExcelUtils.buildExcel("d:/", data));
    }

    @Test
    public void testExcelnull() {
        List<TradeCompanyEntity> data = tradeCompanyRepository.findAll();
        logger.debug("数据：" + data);
        logger.debug("生成excel" + ExcelUtils.buildExcel("d:/", null));
    }

    @Test
    public void testgetE() {
    }
}
