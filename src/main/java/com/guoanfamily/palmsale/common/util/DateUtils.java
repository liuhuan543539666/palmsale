package com.guoanfamily.palmsale.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by yangshaodong on 2017/4/18.
 */
public class DateUtils {

    /** 年月日模式字符串 */
    public static final String YEAR_MONTH_DAY_PATTERN = "yyyy-MM-dd";

    /** 年月日时分秒模式字符串 */
    public static final String YMDHMS_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /** 年月日时分模式字符串 */
    public static final String YMDHM_PATTERN = "yyyy-MM-dd HH:mm";

    /**
     * 将某个日期增加指定年数，并返回结果。如果传入负数，则为减。
     *
     * @param date
     *            要操作的日期对象
     * @param amount
     *            要增加年的数目
     * @return 结果日期对象
     */
    public static Date addYear(final Date date, final int amount) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.YEAR, amount);
        return c.getTime();
    }

    /**
     * 将某个日期增加指定月数，并返回结果。如果传入负数，则为减。
     *
     * @param date
     *            要操作的日期对象
     * @param ammount
     *            要增加月数
     * @return 结果日期对象
     */
    public static Date addMonth(final Date date, final int ammount) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, ammount);
        return c.getTime();
    }

    /**
     * 根据传入的日期格式化patter将传入的字符串转换成日期对象
     *
     * @param dateStr
     *            要转换的字符串
     * @param pattern
     *            日期格式化pattern
     * @return 转换后的日期对象
     * @throws ParseException
     *             如果传入的字符串格式不合法
     */
    public static Date parse(final String dateStr, final String pattern){
        DateFormat df = new SimpleDateFormat(pattern);
        try {
            return df.parse(dateStr);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 根据传入的日期格式化pattern将传入的日期格式化成字符串。
     *
     * @param date
     *            要格式化的日期对象
     * @param pattern
     *            日期格式化pattern
     * @return 格式化后的日期字符串
     */
    public static String format(final Date date, final String pattern) {
        if (null == date) {
            return "";
        }
        DateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);
    }

    /**
     * 获取当前时间
     * @return
     */
    public  static Date getCurrentDate() {
        return  new Date();
    }
}
