package com.guoanfamily.palmsale.common.util;


/**
 * 验证条件规则静态常量类
 *
 * @author AsherLi
 * @version 1.0.00
 */
@SuppressWarnings(value = "unused")
public class RuleConstant {

    // 匹配图象 <br>
    // 格式: /相对路径/文件名.后缀 (后缀为gif,dmp,png)
    // 匹配 : /forum/head_icon/admini2005111_ff.gif 或 admini2005111.dmp<br>
    // 不匹配: c:/admins4512.gif
    public static final String IMAGE = "^(/{0,1}\\w){1,}\\.(gif|dmp|png|jpg)$|^\\w{1,}\\.(gif|dmp|png|jpg)$";

    // 匹配EMAIL地址 <br>
    // 格式: XXX@XXX.XXX.XX
    // 匹配 : foo@bar.com 或 foobar@foobar.com.au <br>
    // 不匹配: foo@bar 或 $$$@bar.com
    public static final String EMAIL = "^[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w]" +
            "(?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?$";

    // 匹配EMAIL地址,加强 <br>
    public static final String EMAIL_2 = "^([0-9a-zA-Z]([-.\\w]*[0-9a-zA-Z])*@(([0-9a-zA-Z])+([-\\w]*[0-9a-zA-Z])*\\" +
            ".)+[a-zA-Z]{2,9})$";

    // 匹配URL <br>
    // 格式: XXXX://XXX.XXX.XXX.XX/XXX.XXX?XXX=XXX
    // 匹配 : http://www.suncer.com 或news://www<br>
    // 不匹配: c:\window
    public static final String URL = "^(\\w+)://([^/:]+)(:\\d*)?([^#\\s]*)$";

    // 匹配HTTP地址
    // 格式: http://XXX.XXX.XXX.XX/XXX.XXX?XXX=XXX 或 ftp://XXX.XXX.XXX 或 https://XXX
    // 匹配 : http://www.suncer.com:8080/index.html?login=true<br>
    // 不匹配: news://www
    public static final String HTTP = "^(http|https|ftp)://([^/:]+)(:\\d*)?([^#\\s]*)$";

    // 匹配HTTP地址,加强
    public static final String HTTP_2 = "^((https|http|ftp|rtsp|mms)?://)?(([0-9a-z_!~*'().&=+$%-]+: )" +
            "?[0-9a-z_!~*'().&=+$%-]+@)?(([0-9]{1,3}\\.){3}[0-9]{1,3}|([0-9a-z_!~*'()-]+\\.)*([0-9a-z][0-9a-z-]" +
            "{0,61})?[0-9a-z]\\.[a-z]{2,6})(:[0-9]{1,4})?((/?)|(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$";

    // 匹配IP地址
    public static final String IPV4 = "^((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)$";

    // 匹配IP地址,加强
    public static final String IPV4_2 = "^\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)" +
            "\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|" +
            "1\\d\\d|2[0-4]\\d|25[0-5])\\b$";

    // 匹配IP地址<br>
    // (简单匹配，格式，如：192.168.1.1，127.0.0.1，没有匹配IP段的大小)
    public static final String IP = "^[1-9](\\d{1,2})?\\.(0|([1-9](\\d{1,2})?))\\.(0|([1-9](\\d{1,2})?))\\." +
            "(0|([1-9](\\d{1,2})?))$";

    // 匹配日期 <br>
    // 格式(首位不为0): XXXX-XX-XX 或 XXXX XX XX 或 XXXX-X-X <br>
    // 范围:1900--2099 <br>
    // 匹配 : 2005-04-04 <br>
    // 不匹配: 01-01-01
    public static final String DATE= "^((((19){1}|(20){1})d{2})|d{2})[-\\s]{1}[01]{1}d{1}[-\\s]{1}" +
            "[0-3]{1}d{1}$";

    // 匹配电话 <br>
    // 格式为: 0XXX-XXXXXX(10-13位首位必须为0) 或0XXX XXXXXXX(10-13位首位必须为0) 或 <br>
    // (0XXX)XXXXXXXX(11-14位首位必须为0) 或 XXXXXXXX(6-8位首位不为0) 或 XXXXXXXXXXX(11位首位不为0) <br>
    // 匹配 : 0371-123456 或 (0371)1234567 或 (0371)12345678 或 010-123456 或 010-12345678 或 12345678912 <br>
    // 不匹配: 1111-134355 或 0123456789
    public static final String PHONE = "^(?:0[0-9]{2,3}[-\\s]{1}|\\(0[0-9]{2,4}\\))[0-9]{6,8}$|^[1-9]{1}[0-9]" +
            "{5,7}$|^[1-9]{1}[0-9]{10}$";

    // 匹配手机号 <br>
    public static final String MOBILE = "^(13[0-9]|14(5|7)|15(0|1|2|3|5|6|7|8|9)|17(6|7|8)|18[0-9])\\d{8}$";

    // 匹配身份证 <br>
    // 格式为: XXXXXXXXXX(10位) 或 XXXXXXXXXXXXX(13位) 或 XXXXXXXXXXXXXXX(15位) 或 XXXXXXXXXXXXXXXXXX(18位) <br>
    // 匹配 : 0123456789123 <br>
    // 不匹配: 0123456
    public static final String ID_CARD = "^(\\d{6})(\\d{4})(\\d{2})(\\d{2})(\\d{3})([0-9]|X|x)$";

    // 匹配邮编代码 <br>
    // 格式为: XXXXXX(6位) <br>
    // 匹配 : 012345 <br>
    // 不匹配: 0123456
    public static final String ZIP_CODE = "^[0-9]{6}$";

    // 不包括特殊字符的匹配 (字符串中不包括符号 数学次方号^ 单引号' 双引号" 分号; 逗号, 帽号: 数学减号- 右尖括号&gt;
    // 左尖括号&lt;  反斜杠\ 即空格,制表符,回车符等 )<br>
    // 格式为: x 或 一个一上的字符 <br>
    // 匹配 : 012345 <br>
    // 不匹配: 0123456
    public static final String NON_SPECIAL_CHAR = "^[^'\"\\;,:-<>\\s].+$";

    // 匹配非负整数（正整数 + 0)
    public static final String NON_NEGATIVE_INTEGERS = "^\\d+$";

    // 匹配不包括零的非负整数（正整数 &gt; 0)
    public static final String NON_ZERO_NEGATIVE_INTEGERS = "^[1-9]+\\d*$";

    // 匹配正整数
    public static final String POSITIVE_INTEGER = "^[0-9]*[1-9][0-9]*$";

    // 匹配非正整数（负整数 + 0）
    public static final String NON_POSITIVE_INTEGERS = "^((-\\d+)|(0+))$";

    // 匹配负整数
    public static final String NEGATIVE_INTEGERS = "^-[0-9]*[1-9][0-9]*$";

    // 匹配整数
    public static final String INTEGER = "^-?\\d+$";

    // 匹配空白符
    public static final String WHITESPACE = "^\\n\\s*\\r$";

    // 匹配双字节字符
    public final static String DOUBLE_BYTE_CHARACTERS = "^[^\\x00-\\xff]$";

    // 匹配非负浮点数（正浮点数 + 0）
    public static final String NON_NEGATIVE_RATIONAL_NUMBERS = "^\\d+(\\.\\d+)?$";

    // 匹配正浮点数
    public static final String POSITIVE_RATIONAL_NUMBERS = "^(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\." +
            "[0-9]+)|([0-9]*[1-9][0-9]*))$";

    // 匹配非正浮点数（负浮点数 + 0）
    public static final String NON_POSITIVE_RATIONAL_NUMBERS = "^((-\\d+(\\.\\d+)?)|(0+(\\.0+)?))$";

    // 匹配负浮点数
    public static final String NEGATIVE_RATIONAL_NUMBERS = "^(-(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\." +
            "[0-9]+)|([0-9]*[1-9][0-9]*)))$";

    // 匹配浮点数
    public static final String RATIONAL_NUMBERS = "^(-?\\d+)(\\.\\d+)?$";

    // 匹配由26个英文字母组成的字符串
    public static final String LETTER = "^[A-Za-z]+$";

    // 匹配由26个英文字母的大写组成的字符串
    public static final String UPWARD_LETTER = "^[A-Z]+$";

    // 匹配由26个英文字母的小写组成的字符串
    public static final String LOWER_LETTER = "^[a-z]+$";

    // 匹配由数字和26个英文字母组成的字符串
    public static final String LETTER_NUMBER = "^[A-Za-z0-9]+$";

    // 匹配由数字、26个英文字母或者下划线组成的字符串
    public static final String LETTER_NUMBER_UNDERLINE = "^\\w+$";

    // 匹配中文
    public static final String CHINESE = "^[\u4e00-\u9fa5]{2,5}$";

    // 验证密码长度
    public static final String PASSWORD_LENGTH = "^\\d{6,18}$";

    // 正则表达式匹配中文
    public final static String RE_CHINESE = "^[\u4E00-\u9FFF]+$";

    // 匹配纯数字
    public final static String PURE_DIGITAL = "^[0-9]+$";

    // 判断是否是日期格式
    public final static String IS_DATE_FORMAT = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|" +
            "(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|\"\"" +
            "([1-2][0-9])|(30)))|(0?2[\\\\-\\\\/\\\\s]?((0?[1-9])|([1-2][0-9])))))|(\\\\d{2}(([02468][1235679])|\"\"" +
            "([13579][01345789]))[\\\\-\\\\/\\\\s]?((((0?[13578])|(1[02]))[\\\\-\\\\/\\\\s]?((0?[1-9])|([1-2][0-9])" +
            "|\"\"(3[01])))|(((0?[469])|(11))[\\\\-\\\\/\\\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\\\-\\\\/\\\\s]?" +
            "((0?[1-9])|\"\"(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:" +
            "([0-5]?[0-9])))))?$";

    // 匹配常规UUID
    public static final String UUID= "^[0-9a-z]{8}-[0-9a-z]{4}-[0-9a-z]{4}-[0-9a-z]{4}-[0-9a-z]{12}$";

    // 英文字母 、数字和下划线
    public final static String GENERAL = "^\\w+$";

     // 中文字、英文字母、数字和下划线
    public final static String GENERAL_WITH_CHINESE ="^[\\u0391-\\uFFE5\\w]+$";

     // 不带横线的UUID
    public final static String UUID_SIMPLE = "^[0-9a-z]{32}$";


}
