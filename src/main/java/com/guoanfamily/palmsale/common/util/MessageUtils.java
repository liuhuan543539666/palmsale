/*
 * Copyright 2016 AsherLi0103
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.guoanfamily.palmsale.common.util;

import com.guoanfamily.palmsale.config.MsgSettings;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 短信发送工具类
 *
 * @author AsherLi0103
 * @version 0.0.01
 */
public class MessageUtils {
    private static final Logger logger = LoggerFactory.getLogger(MessageUtils.class);


    /**
     * 短信发送接口
     *
     * @param modelMessage 短信内容
     * @param phoneNumber  接受者号码
     * @return 发送结果
     */
    public static String messagePostRequest(String modelMessage, String phoneNumber) {
        //判断手机号码是否为空
        if (StringUtils.isBlank(phoneNumber)) {
            return GlobalConstant.SMS_PHONE_NULL;
        }

        //判断手机号码是否正确
        if (!ValidatorUtils.isMobile(phoneNumber)) {
            return GlobalConstant.PHONE_NUMBER_ERROR_MSG;
        }

        //读取配置文件
        MsgSettings util = new MsgSettings();
        String username = util.getUsername();
        String password = util.getPassword();
        String epid = util.getEpid();
        //密码MD5加密
        password = MD5.encryption(password);

        InputStream is = null;
        StringBuilder builder = new StringBuilder();
        try {
            //信息内容进行编码,防止传输乱码
            String covertMessage = URLEncoder.encode(modelMessage, GlobalConstant.SMS_DEFAULT_CHARSET);
            //URL地址生成
            StringBuilder url = new StringBuilder();
            url.append(GlobalConstant.SMS_URL)
                    .append("?username=").append(username)
                    .append("&password=").append(password)
                    .append("&message=").append(covertMessage)
                    .append("&phone=").append(phoneNumber)
                    .append("&epid=").append(epid)
                    .append("&linkid=&subcode=");

            //连接HL95发送信息
            SimpleClientHttpRequestFactory schr = new SimpleClientHttpRequestFactory();
            ClientHttpRequest chr = schr.createRequest(new URI(url.toString()), HttpMethod.GET);
            ClientHttpResponse res = chr.execute();

            //获取返回消息
            is = res.getBody();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            String line = null;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            logger.info("短信发送详细信息: " + builder + " \n规则: \n\t00[成功],\n\t1[参数不完整]," +
                    "\n\t2[鉴权失败],\n\t3[批量发出数据超过50],\n\t4[发送失败],\n\t5[余额不足]," +
                    "\n\t6[发送内容含屏蔽词],\n\t7[短信内容超过350字符]");


        } catch (URISyntaxException e) {
            logger.error("URI解析异常: ", e);
        } catch (IOException e) {
            logger.error("返回结果读取异常: ", e);
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //判断返回信息结果
       return builder.toString();
    }


    /**
     * 根据短息模板生成短信息
     *
     * @param messageInfo 短信模板 例如:[验证码为：{1}，手机号为：{2}]
     * @param args        参数
     * @return 完整短信息
     */
    public static String createMessageModel(String messageInfo, String... args) {
        String str = messageInfo.replaceAll("[0-9]+", "&");
        String E1 = "\\{" + "\\&" + "\\}";
        String[] s1 = str.split(E1);
        if (args != null) {
            if (args.length == s1.length || args.length == (s1.length - 1)) {
                for (int i = 0; i < args.length; i++) {
                    if (args[i] != null) {
                        messageInfo = messageInfo.replace("{" + (i + 1) + "}", args[i]);
                    }
                }
            } else {
                throw new RuntimeException();
            }
        }
        return messageInfo;
    }

    /**
     * 六位随机验证码
     * @return
     */
    public static String  getRandomAuthenticationCode(){
        Random random = new Random();
        int x = random.nextInt(899999);
        x = x + 100000;
        return String.valueOf(x);
    }

    /**
     * 定时删除Session存储指定内容
     * @param session
     * @param code
     * @throws Exception
     */
    public static void deleteSessionCode(final HttpSession session, final String code) throws Exception {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                try {
                    session.removeAttribute(code);
                } catch (Exception e) {
                }
            }
        }, 180000);// 设定指定的时间time,此处为3分钟
    }

    //测试方法
    public static void main(String[] args) {
//        messagePostRequest("测试短信ffff发送2dff4324wwの221", "13661015962");
        messagePostRequest("测试短信2发ssssdswww送", "18401636111");
    }
}
