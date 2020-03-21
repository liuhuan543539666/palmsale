package com.guoanfamily.palmsale.common.util;


import com.guoanfamily.palmsale.common.error.ValidateException;

import java.net.MalformedURLException;
import java.util.regex.Pattern;


/**
 * 字段验证器
 *
 * @author Asherli
 */
@SuppressWarnings("unused")
public class ValidatorUtils {

    /**
     * 通过正则表达式验证
     *
     * @param regex 正则
     * @param value 值
     * @return 是否匹配正则
     */
    public static boolean isMactchRegex(String regex, String value) {
        return isMatch(regex, value);
    }

    /**
     * 通过正则表达式验证<br>
     * 不符合正则
     *
     * @param regex    正则
     * @param value    值
     * @param errorMsg 验证错误的信息
     * @throws ValidateException e
     */
    public static void validateMatchRegex(String regex, String value, String errorMsg) throws ValidateException {
        if (!isMactchRegex(regex, value)) {
            throw new ValidateException(errorMsg);
        }
    }

    /**
     * 通过正则表达式验证
     *
     * @param pattern 正则模式
     * @param value   值
     * @return 是否匹配正则
     */
    public static boolean isMactchRegex(Pattern pattern, String value) {
        return isMatch(pattern, value);
    }

    /**
     * 给定内容是否匹配正则
     *
     * @param regex   正则
     * @param content 内容
     * @return 正则为null或者""则不检查，返回true，内容为null返回false
     */
    public static boolean isMatch(String regex, String content) {
        if (content == null) {
            //提供null的字符串为不匹配
            return false;
        }

        //正则不存在则为全匹配
        return StringUtil.isEmpty(regex) || Pattern.matches(regex, content);

    }

    /**
     * 给定内容是否匹配正则
     *
     * @param pattern 模式
     * @param content 内容
     * @return 正则为null或者""则不检查，返回true，内容为null返回false
     */
    public static boolean isMatch(Pattern pattern, String content) {
        //提供null的字符串为不匹配
        return !(content == null || pattern == null) && pattern.matcher(content).matches();
    }

    /**
     * 验证是否为英文字母 、数字和下划线
     *
     * @param value 值
     * @return 是否为英文字母 、数字和下划线
     */
    public static boolean isGeneral(String value) {
        return isMactchRegex(Pattern.compile(RuleConstant.GENERAL), value);
    }

    /**
     * 验证是否为英文字母 、数字和下划线
     *
     * @param value    值
     * @param errorMsg 验证错误的信息
     * @throws ValidateException e
     */
    public static void validateGeneral(String value, String errorMsg) throws ValidateException {
        if (!isGeneral(value)) {
            throw new ValidateException(errorMsg);
        }
    }

    /**
     * 验证是否为给定长度范围的英文字母 、数字和下划线
     *
     * @param value 值
     * @param min   最小长度，负数自动识别为0
     * @param max   最大长度，0或负数表示不限制最大长度
     * @return 是否为给定长度范围的英文字母 、数字和下划线
     */
    public static boolean isGeneral(String value, int min, int max) {
        String reg = "^\\w{" + min + "," + max + "}$";
        if (min < 0) {
            min = 0;
        }
        if (max <= 0) {
            reg = "^\\w{" + min + ",}$";
        }
        return isMactchRegex(reg, value);
    }

    /**
     * 验证是否为给定长度范围的英文字母 、数字和下划线
     *
     * @param value    值
     * @param min      最小长度，负数自动识别为0
     * @param max      最大长度，0或负数表示不限制最大长度
     * @param errorMsg 验证错误的信息
     * @throws ValidateException e
     */
    public static void validateGeneral(String value, int min, int max, String errorMsg) throws ValidateException {
        if (!isGeneral(value, min, max)) {
            throw new ValidateException(errorMsg);
        }
    }

    /**
     * 验证是否为给定最小长度的英文字母 、数字和下划线
     *
     * @param value 值
     * @param min   最小长度，负数自动识别为0
     * @return 是否为给定最小长度的英文字母 、数字和下划线
     */
    public static boolean isGeneral(String value, int min) {
        return isGeneral(value, min, 0);
    }

    /**
     * 验证是否为给定最小长度的英文字母 、数字和下划线
     *
     * @param value    值
     * @param min      最小长度，负数自动识别为0
     * @param errorMsg 验证错误的信息
     * @throws ValidateException e
     */
    public static void validateGeneral(String value, int min, String errorMsg) throws ValidateException {
        validateGeneral(value, min, 0, errorMsg);
    }

    /**
     * 验证该字符串是否是数字
     *
     * @param value 字符串内容
     * @return 是否是数字
     */
    public static boolean isNumber(String value) {
        return !StringUtil.isBlank(value) && isMactchRegex(Pattern.compile(RuleConstant.NON_NEGATIVE_INTEGERS),
                value);
    }

    /**
     * 验证是否为数字
     *
     * @param value    表单值
     * @param errorMsg 验证错误的信息
     * @throws ValidateException e
     */
    public static void validateNumbers(String value, String errorMsg) throws ValidateException {
        if (!isNumber(value)) {
            throw new ValidateException(errorMsg);
        }
    }

    /**
     * 验证是否为货币
     *
     * @param value 值
     * @return 是否为货币
     */
    public static boolean isMoney(String value) {
        return isMactchRegex(Pattern.compile(RuleConstant.RATIONAL_NUMBERS), value);
    }

    /**
     * 验证是否为货币
     *
     * @param value    值
     * @param errorMsg 验证错误的信息
     * @throws ValidateException e
     */
    public static void validateMoney(String value, String errorMsg) throws ValidateException {
        if (!isMoney(value)) {
            throw new ValidateException(errorMsg);
        }
    }

    /**
     * 验证是否为邮政编码（中国）
     *
     * @param value 值
     * @return 是否为邮政编码（中国）
     */
    public static boolean isZipCode(String value) {
        return isMactchRegex(Pattern.compile(RuleConstant.ZIP_CODE), value);
    }

    /**
     * 验证是否为邮政编码（中国）
     *
     * @param value    表单值
     * @param errorMsg 验证错误的信息
     * @throws ValidateException e
     */
    public static void validateZipCode(String value, String errorMsg) throws ValidateException {
        if (!isZipCode(value)) {
            throw new ValidateException(errorMsg);
        }
    }

    /**
     * 验证是否为可用邮箱地址
     *
     * @param value 值
     * @return 否为可用邮箱地址
     */
    public static boolean isEmail(String value) {
        return isMactchRegex(Pattern.compile(RuleConstant.EMAIL), value);
    }

    /**
     * 验证是否为可用邮箱地址
     *
     * @param value    值
     * @param errorMsg 验证错误的信息
     * @throws ValidateException e
     */
    public static void validateEmail(String value, String errorMsg) throws ValidateException {
        if (!isEmail(value)) {
            throw new ValidateException(errorMsg);
        }
    }

    /**
     * 验证是否为手机号码（中国）
     *
     * @param value 值
     * @return 是否为手机号码（中国）
     */
    public static boolean isMobile(String value) {
        return isMactchRegex(Pattern.compile(RuleConstant.MOBILE), value);
    }

    /**
     * 验证是否为手机号码（中国）
     *
     * @param value    值
     * @param errorMsg 验证错误的信息
     * @throws ValidateException e
     */
    public static void validateMobile(String value, String errorMsg) throws ValidateException {
        if (!isMobile(value)) {
            throw new ValidateException(errorMsg);
        }
    }

    /**
     * 验证是否为身份证号码（18位中国）<br>
     * 出生日期只支持到到2999年
     *
     * @param value 值
     * @return 是否为身份证号码（18位中国）
     */
    public static boolean isCitizenId(String value) {
        return isMactchRegex(Pattern.compile(RuleConstant.ID_CARD), value);
    }

    /**
     * 验证是否为身份证号码（18位中国）<br>
     * 出生日期只支持到到2999年
     *
     * @param value    值
     * @param errorMsg 验证错误的信息
     * @throws ValidateException e
     */
    public static void validateCitizenIdNumber(String value, String errorMsg) throws ValidateException {
        if (!isCitizenId(value)) {
            throw new ValidateException(errorMsg);
        }
    }

    /**
     * 验证是否为生日<br>
     *
     * @param value 值
     * @return 是否为生日
     */
    public static boolean isBirthday(String value) {
        if (isMactchRegex(Pattern.compile(RuleConstant.IS_DATE_FORMAT), value)) {
            int year = Integer.parseInt(value.substring(0, 4));
            int month = Integer.parseInt(value.substring(5, 7));
            int day = Integer.parseInt(value.substring(8, 10));

            if (month < 1 || month > 12) {
                return false;
            }
            if (day < 1 || day > 31) {
                return false;
            }
            if ((month == 4 || month == 6 || month == 9 || month == 11) && day == 31) {
                return false;
            }
            if (month == 2) {
                boolean isleap = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
                if (day > 29 || (day == 29 && !isleap)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 验证验证是否为生日<br>
     *
     * @param value    值
     * @param errorMsg 验证错误的信息
     * @throws ValidateException e
     */
    public static void validateBirthday(String value, String errorMsg) throws ValidateException {
        if (!isBirthday(value)) {
            throw new ValidateException(errorMsg);
        }
    }

    /**
     * 验证是否为IPV4地址
     *
     * @param value 值
     * @return 是否为IPV4地址
     */
    public static boolean isIpv4(String value) {
        return isMactchRegex(Pattern.compile(RuleConstant.IPV4), value);
    }

    /**
     * 验证是否为IPV4地址
     *
     * @param value    值
     * @param errorMsg 验证错误的信息
     * @throws ValidateException e
     */
    public static void validateIpv4(String value, String errorMsg) throws ValidateException {
        if (!isIpv4(value)) {
            throw new ValidateException(errorMsg);
        }
    }

    /**
     * 验证是否为URL
     *
     * @param value 值
     * @return 是否为URL
     */
    public static boolean isUrl(String value) {
        try {
            new java.net.URL(value);
        } catch (MalformedURLException e) {
            return false;
        }
        return true;
    }

    /**
     * 验证是否为URL
     *
     * @param value    值
     * @param errorMsg 验证错误的信息
     * @throws ValidateException e
     */
    public static void validateUrl(String value, String errorMsg) throws ValidateException {
        if (!isUrl(value)) {
            throw new ValidateException(errorMsg);
        }
    }

    /**
     * 验证是否为汉字
     *
     * @param value 值
     * @return 是否为汉字
     */
    public static boolean isChinese(String value) {
        return isMactchRegex(RuleConstant.RE_CHINESE, value);
    }

    /**
     * 验证是否为汉字
     *
     * @param value    表单值
     * @param errorMsg 验证错误的信息
     * @throws ValidateException e
     */
    public static void validateChinese(String value, String errorMsg) throws ValidateException {
        if (!isChinese(value)) {
            throw new ValidateException(errorMsg);
        }
    }

    /**
     * 验证是否为中文字、英文字母、数字和下划线
     *
     * @param value 值
     * @return 是否为中文字、英文字母、数字和下划线
     */
    public static boolean isGeneralWithChinese(String value) {
        return isMactchRegex(Pattern.compile(RuleConstant.GENERAL_WITH_CHINESE), value);
    }

    /**
     * 验证是否为中文字、英文字母、数字和下划线
     *
     * @param value    值
     * @param errorMsg 验证错误的信息
     * @throws ValidateException e
     */
    public static void validateGeneralWithChinese(String value, String errorMsg) throws ValidateException {
        if (!isGeneralWithChinese(value)) {
            throw new ValidateException(errorMsg);
        }
    }

    /**
     * 验证是否为UUID<br>
     * 包括带横线标准格式和不带横线的简单模式
     *
     * @param value 值
     * @return 是否为UUID
     */
    public static boolean isUUID(String value) {
        return isMactchRegex(Pattern.compile(RuleConstant.UUID), value) || isMactchRegex(Pattern.compile(RuleConstant
                .UUID_SIMPLE), value);
    }

    /**
     * 验证是否为UUID<br>
     * 包括带横线标准格式和不带横线的简单模式
     *
     * @param value    值
     * @param errorMsg 验证错误的信息
     * @throws ValidateException e
     */
    public static void validateUUID(String value, String errorMsg) throws ValidateException {
        if (!isUUID(value)) {
            throw new ValidateException(errorMsg);
        }
    }

    public static boolean isChineseName(String value){
        return isMactchRegex(Pattern.compile(RuleConstant.CHINESE),value);
    }
}
