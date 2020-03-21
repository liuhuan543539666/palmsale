package com.guoanfamily.palmsale.generator;

import com.guoanfamily.palmsale.common.abstractobj.ApiController;
import com.guoanfamily.palmsale.generator.main.GeneratorMain;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * <p>
 * 代码生成器执行demo，由于是基于springioc的依赖注入，所以不能再main方法执行，再springboot环境下执行
 * </p>
 *
 * @auth: 张文旭
 * @time: 2017/6/9 9:48
 * @version: 1.0
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class GeneratorTest {
    private static Logger logger = LoggerFactory.getLogger(GeneratorTest.class);
    @Autowired
    GeneratorMain generatorMain;


    @Test
    public void testAutoCreate() throws IOException {
        logger.debug("代码生成器执行demo");
        String tableNmae = "trade_company";
        String packagePath = "com.guoanfamily.palmsale.xx";
        String urlMapping = ApiController.PARTNER_URL;
        String author = "";
        String version = "";
        String tablePrefix = "";
        generatorMain.generatorCode(tableNmae, packagePath, urlMapping, author, version, tablePrefix);
    }
}
