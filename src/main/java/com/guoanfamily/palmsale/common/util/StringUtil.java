/*
 * Copyright 2016 Asherli(Lzz0103@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.guoanfamily.palmsale.common.util;


import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.io.StringReader;
import java.io.StringWriter;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.*;

/**
 * 字符串工具类
 *
 * @author AsherLi
 * @version 1.0.00
 */
@SuppressWarnings("unused")
public abstract class StringUtil extends StringUtils {

    // Logger 日志
    private static final Logger logger = LoggerFactory.getLogger(StringUtil.class);
    // 默认分隔符
    private static final char DEFAULT_DELIMITERS = ',';
    // 空格
    public static final String SPACE = " ";
    // 逗号
    public static final char COMMA = ',';
    // 空字符串
    public static final String EMPTY = "";

    // 构造器
    public StringUtil() {
        super();
    }

    //Empty String
    // ---------------------------------------- Empty Start ------------------------------------

    public static boolean isAnyEmpty(final CharSequence... css) {
        if (css == null || css.length == 0) {
            return true;
        }
        for (final CharSequence cs : css) {
            if (StringUtils.isEmpty(cs)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNoneEmpty(final CharSequence... css) {
        return !isAnyEmpty(css);
    }

    // 当给定字符串为空字符串时，转换为<code>null</code>
    public static String emptyToNull(final CharSequence cs) {
        return StringUtils.isEmpty(cs) ? null : cs.toString();
    }

    // 是否包含空字符串
    public static boolean hasEmpty(final CharSequence... css) {
        if (css == null || css.length == 0) {
            return true;
        }

        for (CharSequence cs : css) {
            if (StringUtils.isEmpty(cs)) {
                return true;
            }
        }
        return false;
    }

    // 是否全部为空字符串
    public static boolean isAllEmpty(final CharSequence... css) {
        if (css == null || css.length == 0) {
            return true;
        }

        for (CharSequence cs : css) {
            if (StringUtils.isNotEmpty(cs)) {
                return false;
            }
        }
        return true;
    }

    // 当给定字符串为null时，转换为Empty
    public static String nullToEmpty(final CharSequence cs) {
        return nullToDefault(cs, EMPTY);
    }

    // 如果字符串是<code>null</code>，则返回指定默认字符串，否则返回字符串本身。
    public static String nullToDefault(final CharSequence cs, String defaultStr) {
        return (cs == null) ? defaultStr : cs.toString();
    }
    //----------------------------------------Empty End --------------------------------------------

    // Blank String
    // ----------------------------------------Blank Start ------------------------------------------------

    public static boolean isAnyBlank(final CharSequence... css) {
        if (css == null || css.length == 0) {
            return true;
        }
        for (final CharSequence cs : css) {
            if (StringUtils.isBlank(cs)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNoneBlank(final CharSequence... css) {
        return !isAnyBlank(css);
    }

    // 是否包含空字符串
    public static boolean hasBlank(final CharSequence... css) {
        if (css == null || css.length == 0) {
            return true;
        }

        for (CharSequence cs : css) {
            if (StringUtils.isBlank(cs)) {
                return true;
            }
        }
        return false;
    }

    // 给定所有字符串是否为空白
    public static boolean isAllBlank(String... strs) {
        if (strs == null || strs.length == 0) {
            return true;
        }

        for (String str : strs) {
            if (StringUtils.isNotBlank(str)) {
                return false;
            }
        }
        return true;
    }
    // ------------------------------------------ Blank End ---------------------------------------

    // toString
    //------------------------------------------- toString Start --------------------------------------

    // <p>进行tostring操作，如果传入的是null，返回空字符串。</p>
    public static String toString(Object obj) {
        return obj == null ? "" : obj.toString();
    }

    // <p>进行tostring操作，如果传入的是null，返回指定的默认值。</p>
    public static String toString(String nullStr, Object obj) {
        return obj == null ? nullStr : obj.toString();
    }

    public static String toString(Object obj, String charsetName) {
        return toString(obj, Charset.forName(charsetName));
    }

    // 将byte数组转为字符串
    public static String toString(final byte[] bytes, final String charsetName) {
        return toString(bytes, StringUtils.isBlank(charsetName) ? Charset.defaultCharset() : Charset.forName(charsetName));
    }

    // 将编码的byteBuffer数据转换为字符串
    public static String toString(final ByteBuffer data, final String charsetName) {
        if (data == null) {
            return null;
        }

        return toString(data, Charset.forName(charsetName));
    }

    private static String toString(Object obj, Charset charset) {
        if (null == obj) {
            return null;
        }

        if (obj instanceof byte[] || obj instanceof Byte[]) {
            return toString(obj, charset);
        }

        if (obj instanceof ByteBuffer) {
            return toString((ByteBuffer) obj, charset);
        }

        if (obj.getClass().isArray()) {
            return Arrays.toString((Object[]) obj);
        }

        return obj.toString();
    }

    // 解码字节码
    public static String toString(final byte[] bytes, final Charset charset) {
        if (bytes == null) {
            return null;
        }

        if (null == charset) {
            return new String(bytes, Charset.defaultCharset());
        }
        return new String(bytes, charset);
    }

    // 将编码的byteBuffer数据转换为字符串
    public static String toString(final ByteBuffer data, Charset charset) {
        if (null == charset) {
            charset = Charset.defaultCharset();
        }
        return charset.decode(data).toString();
    }
    //------------------------------------------- toString End --------------------------------------

    /**
     * 功能：将全角的符号转换成半角符号.(即中文字符转英文字符)
     *
     * @param str 源字符串
     * @return String
     */
    public static String changeToHalf(String str) {
        String[] decode = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l"
                , "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U"
                , "V", "W", "X", "Y", "Z", "-", "_", "=", "+", "\\", "|", "[", "]", ";", ":", "'", "\"", ",", "<", ".", ">", "/", "?"};
        String source = "１２３４５６７８９０！＠＃＄％︿＆＊（）ａｂｃｄｅｆｇｈｉｊｋｌｍｎｏｐｑｒｓｔｕｖｗｘｙｚＡＢＣＤＥＦＧＨＩＪＫＬＭＮＯＰＱＲＳＴＵＶＷＸＹＺ－＿＝＋＼｜【】；：'\\，〈。〉／？";
        String result = "";
        for (int i = 0; i < str.length(); i++) {
            int pos = source.indexOf(str.charAt(i));
            if (pos != -1) {
                result += decode[pos];
            } else {
                result += str.charAt(i);
            }
        }
        return result;
    }

    /**
     * 功能：将半角的符号转换成全角符号.(即英文字符转中文字符)
     *
     * @param str 源字符串
     * @return String
     */
    public static String changeToFull(String str) {
        String source = "1234567890!@#$%^&*()abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-_=+\\|[];:'\",<.>/?";
        String[] decode = {"１", "２", "３", "４", "５", "６", "７", "８", "９", "０",
                "！", "＠", "＃", "＄", "％", "︿", "＆", "＊", "（", "）", "ａ", "ｂ",
                "ｃ", "ｄ", "ｅ", "ｆ", "ｇ", "ｈ", "ｉ", "ｊ", "ｋ", "ｌ", "ｍ", "ｎ",
                "ｏ", "ｐ", "ｑ", "ｒ", "ｓ", "ｔ", "ｕ", "ｖ", "ｗ", "ｘ", "ｙ", "ｚ",
                "Ａ", "Ｂ", "Ｃ", "Ｄ", "Ｅ", "Ｆ", "Ｇ", "Ｈ", "Ｉ", "Ｊ", "Ｋ", "Ｌ",
                "Ｍ", "Ｎ", "Ｏ", "Ｐ", "Ｑ", "Ｒ", "Ｓ", "Ｔ", "Ｕ", "Ｖ", "Ｗ", "Ｘ",
                "Ｙ", "Ｚ", "－", "＿", "＝", "＋", "＼", "｜", "【", "】", "；", "：",
                "'", "\"", "，", "〈", "。", "〉", "／", "？"};
        String result = "";
        for (int i = 0; i < str.length(); i++) {
            int pos = source.indexOf(str.charAt(i));
            if (pos != -1) {
                result += decode[pos];
            } else {
                result += str.charAt(i);
            }
        }
        return result;
    }

    public static String unicodeEscaped(char ch) {
        if (ch < 0x10) {
            return "\\u000" + Integer.toHexString(ch);
        } else if (ch < 0x100) {
            return "\\u00" + Integer.toHexString(ch);
        } else if (ch < 0x1000) {
            return "\\u0" + Integer.toHexString(ch);
        }
        return "\\u" + Integer.toHexString(ch);
    }

    public static String removePrefix(String str, String prefix) {
        if (StringUtils.isEmpty(str) || StringUtils.isEmpty(prefix)) {
            return str;
        }

        if (str.startsWith(prefix)) {
            return str.substring(prefix.length());
        }
        return str;
    }

    public static String removePrefixIgnoreCase(String str, String prefix) {
        if (StringUtils.isEmpty(str) || StringUtils.isEmpty(prefix)) {
            return str;
        }

        if (str.toLowerCase().startsWith(prefix.toLowerCase())) {
            return str.substring(prefix.length());
        }
        return str;
    }

    public static String removeSuffix(String str, String suffix) {
        if (StringUtils.isEmpty(str) || StringUtils.isEmpty(suffix)) {
            return str;
        }

        if (str.endsWith(suffix)) {
            return str.substring(0, str.length() - suffix.length());
        }
        return str;
    }

    public static String removeSuffixIgnoreCase(String str, String suffix) {
        if (StringUtils.isEmpty(str) || StringUtils.isEmpty(suffix)) {
            return str;
        }

        if (str.toLowerCase().endsWith(suffix.toLowerCase())) {
            return str.substring(0, str.length() - suffix.length());
        }
        return str;
    }

    public static String cleanBlank(String str) {
        if (str == null) {
            return null;
        }

        return str.replaceAll("\\s*", EMPTY);
    }


    public static boolean isSurround(String str, String prefix, String suffix) {
        return !StringUtils.isBlank(str) && str.length() >= (prefix.length() + suffix.length()) && str.startsWith(prefix) && str.endsWith(suffix);

    }

    public static boolean isSurround(String str, char prefix, char suffix) {
        return !StringUtils.isBlank(str) && str.length() >= 2 && str.charAt(0) == prefix && str.charAt(str.length() - 1) == suffix;

    }

    public static boolean equals(String str1, String str2) {
        if (str1 == null) {
            return str2 == null;
        }

        return str1.equals(str2);
    }

    public static boolean equalsIgnoreCase(String str1, String str2) {
        if (str1 == null) {
            return str2 == null;
        }

        return str1.equalsIgnoreCase(str2);
    }

    /**
     * 格式化文本
     *
     * @param template 文本模板，被替换的部分用 {} 表示
     * @param values   参数值
     * @return 格式化后的文本
     */
    public static String format(String template, Object... values) {
        if ((values != null ? values.length : 0) == 0 || isBlank(template)) {
            return template;
        }

        final StringBuilder sb = new StringBuilder();
        final int length = template.length();

        int valueIndex = 0;
        char currentChar;
        for (int i = 0; i < length; i++) {
            if (valueIndex >= values.length) {
                sb.append(substring(template, i, length));
                break;
            }

            currentChar = template.charAt(i);
            if (currentChar == '{') {
                final char nextChar = template.charAt(++i);
                if (nextChar == '}') {
                    sb.append(values[valueIndex++]);
                } else {
                    sb.append('{').append(nextChar);
                }
            } else {
                sb.append(currentChar);
            }

        }

        return sb.toString();
    }

    public static String format(String template, Map<?, ?> map) {
        if (null == map || map.isEmpty()) {
            return template;
        }

        for (Map.Entry<?, ?> entry : map.entrySet()) {
            template = template.replace("{" + entry.getKey() + "}", entry.getValue().toString());
        }
        return template;
    }

    public static String wrap(String str, String prefix, String suffix) {
        return format("{}{}{}", prefix, str, suffix);
    }

    public static boolean isWrap(String str, String prefix, String suffix) {
        return str.startsWith(prefix) && str.endsWith(suffix);
    }

    public static boolean isWrap(String str, String wrapper) {
        return isWrap(str, wrapper, wrapper);
    }

    public static boolean isWrap(String str, char wrapper) {
        return isWrap(str, wrapper, wrapper);
    }

    public static boolean isWrap(String str, char prefixChar, char suffixChar) {
        return str.charAt(0) == prefixChar && str.charAt(str.length() - 1) == suffixChar;
    }

    public static String padPre(String str, int minLength, char padChar) {
        if (str.length() >= minLength) {
            return str;
        }
        StringBuilder sb = new StringBuilder(minLength);
        for (int i = str.length(); i < minLength; i++) {
            sb.append(padChar);
        }
        sb.append(str);
        return sb.toString();
    }

    public static String padEnd(String str, int minLength, char padChar) {
        if (str.length() >= minLength) {
            return str;
        }
        StringBuilder sb = new StringBuilder(minLength);
        sb.append(str);
        for (int i = str.length(); i < minLength; i++) {
            sb.append(padChar);
        }
        return sb.toString();
    }

    public static StringBuilder builder(String... strs) {
        final StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            sb.append(str);
        }
        return sb;
    }

    public static StringReader getReader(String str) {
        return new StringReader(str);
    }

    public static StringWriter getWriter() {
        return new StringWriter();
    }


    public static String betweenTwoLetters(String str, CharSequence first, CharSequence second) {
        if (StringUtils.isEmpty(str)) {
            return EMPTY;
        }
        int firstIndex = StringUtils.indexOf(str, first);
        if (firstIndex == -1) {
            return str;
        }
        int secondIndex = StringUtils.indexOf(str, second);
        if (secondIndex == -1) {
            return str;
        }
        return StringUtils.substring(str, firstIndex + 1, secondIndex);
    }

    public static <T> List<T> convert(String str, String character, StringParse<T> parse) {
        if (StringUtils.isEmpty(str) || StringUtils.isEmpty(character) || parse == null) {
            return Collections.emptyList();
        }

        String[] temps = StringUtils.split(str, character);
        List<T> result = new ArrayList<>();
        for (String t : temps) {
            result.add(parse.parse(t));
        }

        return result;
    }

    public static <T> List<T> convert(String str, StringParse<T> parse) {
        return convert(str, String.valueOf(DEFAULT_DELIMITERS), parse);
    }

    public static String[] convert(List<?> list, boolean containNull) {
        if (list.isEmpty()) {
            return new String[]{};
        }

        List<String> strList = new ArrayList<>();
        for (Object obj : list) {
            if (obj == null && !containNull) {
                continue;
            }

            if (obj == null) {
                strList.add(StringUtils.EMPTY);
            } else {
                strList.add(obj.toString());
            }
        }

        return strList.toArray(new String[strList.size()]);
    }

    public static int[] strArr2IntArr(String[] str) {
        int[] target = new int[str.length];
        for (int i = 0; i < str.length; i++) {
            Assert.isTrue(StringUtils.isEmpty(str[i]), "String[]数组转int[]数组时,第[" + i + "]位不可以为[" + str[i] + "]");
            target[i] = Integer.parseInt(str[i]);
        }
        return target;
    }

    public static float[] strArr2FloatArr(String[] str) {
        float[] target = new float[str.length];
        for (int i = 0; i < str.length; i++) {
            Assert.isTrue(StringUtils.isEmpty(str[i]), "String[]数组转float[]数组时,第[" + i + "]位不可以为[" + str[i] + "]");
            target[i] = Float.parseFloat(str[i]);
        }
        return target;
    }

    public static double[] strArr2DoubleArr(String[] str) {
        double[] target = new double[str.length];
        for (int i = 0; i < str.length; i++) {
            Assert.isTrue(StringUtils.isEmpty(str[i]), "String[]数组转double[]数组时,第[" + i + "]位不可以为[" + str[i] + "]");
            target[i] = Double.parseDouble(str[i]);
        }
        return target;
    }

    public static long[] strArr2LongArr(String[] str) {
        long[] target = new long[str.length];
        for (int i = 0; i < str.length; i++) {
            Assert.isTrue(StringUtils.isEmpty(str[i]), "String[]数组转longt[]数组时,第[" + i + "]位不可以为[" + str[i] + "]");
            target[i] = Long.parseLong(str[i]);
        }
        return target;
    }

    public static Date[] strArr2DateArr(String[] str, DateFormat format) {
        Date[] target = new Date[str.length];
        for (int i = 0; i < str.length; i++) {
            try {
                Assert.isTrue(StringUtils.isEmpty(str[i]), "String[]数组转Date[]数组时,第[" + i + "]位不可以为[" + str[i] + "]");
                target[i] = format.parse(str[i]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return target;
    }

    /**
     * String 数组转换 short 数组
     *
     * @param str 需要转换的String 数组
     * @return short 数组
     */
    public static Object strArr2ShortArr(String[] str) {
        short[] target = new short[str.length];
        for (int i = 0; i < str.length; i++) {
            Assert.isTrue(StringUtils.isEmpty(str[i]), "String[]数组转short[]数组时,第[" + i + "]位不可以为[" + str[i] + "]");
            target[i] = Short.parseShort(str[i]);
        }
        return target;
    }

    /**
     * 根据大写字母分割字符
     * @param fields
     * @return
     */
    public static String[] splitByUpperCase(String fields) {
        return fields.split("(?=[A-Z])");
    }

    /**
     * 类型转换器的接口.
     *
     * @param <V> d
     */
    protected interface StringParse<V> {
        /**
         * 转换
         *
         * @param str 待转换的数据
         * @return 转换后的数据
         */
        V parse(String str);
    }


}
