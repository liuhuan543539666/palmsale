package com.guoanfamily.palmsale.bean;

import com.guoanfamily.palmsale.xx.entity.TradeCompanyEntity;
import com.guoanfamily.palmsale.xx.repository.TradeCompanyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.InvocationTargetException;

/**
 * <p>
 * 一句话描述你写的是什么玩意？
 * </p>
 *
 * @auth: 张文旭
 * @time: 2017/6/19 9:56
 * @version: 1.0
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class BeanTes {
    private static Logger logger = LoggerFactory.getLogger(BeanTes.class);
    @Autowired
    TradeCompanyRepository tradeCompanyRepository;

    @Test
    public void test2Map() throws InvocationTargetException, IllegalAccessException {
        String id = "297e94d65c7bf099015c7bf1c2000003";
        TradeCompanyEntity entity = tradeCompanyRepository.findOne(id);
        logger.debug("bean结果集：" + entity);
        logger.debug("bean转换成map结果集:" + entity.ToMap());
    }

    @Test
    public void test2Json() {
        String id = "297e94d65c7bf099015c7bf1c2000003";
        TradeCompanyEntity entity = tradeCompanyRepository.findOne(id);
        logger.debug("bean结果集：" + entity);
        logger.debug("bean转换成json结果集:" + entity.ToJson());
    }

    @Test
    public void test2Xml() {
        String id = "297e94d65c7bf099015c7bf1c2000003";
        TradeCompanyEntity entity = tradeCompanyRepository.findOne(id);
        logger.debug("bean结果集：" + entity);
        logger.debug("bean转换成json结果集:" + entity.ToXml());
    }
}
