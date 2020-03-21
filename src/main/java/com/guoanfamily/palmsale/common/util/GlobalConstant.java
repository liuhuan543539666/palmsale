package com.guoanfamily.palmsale.common.util;

/**
 * 全局常量类
 *
 * @author AsherLi0103
 * @version 0.0.02
 */
public class GlobalConstant {


    //-----------------服务配置常量-------------------------
    //该类常量谨慎修改

    /* 短信服务器URL */
    public static final String SMS_URL = "http://q.hl95.com:8061/";

    //------------------信息提示类常量----------------------

    /* 默认编码 */
    public static final String SMS_DEFAULT_CHARSET = "GB2312";
    /* 短信发送成功提示信息 */
    public static final String SMS_SUCCESS = "信息发送成功,请注意查收";
    /* 短信发送失败提示信息 */
    public static final String SMS_FAILURE = "信息发送失败,请重试";
    /* 未输入手机号错误提示信息 */
    public static final String SMS_PHONE_NULL = "请输入手机号码";
    /* 短信内容过长 */
    public static final String SMS_LENGHT_MAX = "短信内容超过350字符";
    /* 短信内容含有屏蔽词 */
    public static final String SMS_WORDS_SHIELD = "发送内容含屏蔽词";
    /* 手机号错误提示信息 */
    public static final String PHONE_NUMBER_ERROR_MSG = "请输入正确的手机号码";




}
