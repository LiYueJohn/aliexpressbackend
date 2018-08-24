package com.aliexpress.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by tanghong on 17-5-5.
 */
public class Validation {
    private static final Logger LOGGER = LoggerFactory.getLogger(Validation.class);

    /**
     * 验证整数的正则式
     */
    private static final String P_INT = "^\\d+$";
    /**
     * 验证浮点数的正则式
     */
    private static final String P_FLOAT = "^\\d+(\\.\\d+){0,1}$";
    /**
     * 验证电话号码的正则式
     */
    private static final String P_PHONE = "^\\d+(-\\d+)*$";
    /**
     * 验证 e-mail 的正则式
     */
    private static final String P_EMAIL = "^([\\w-]+(?:\\.[\\w-]+)*)@((?:[\\w-]+\\.)*\\w[\\w-]{0,66})\\.([a-z]{2,6}(?:\\.[a-z]{2})?)$";

    /**
     * 验证是否为整数
     */
    public static final int INT = 1;
    /**
     * 验证是否为浮点数
     */
    public static final int FLOAT = 2;
    /**
     * 验证是否为电话号码
     */
    public static final int PHONE = 3;
    /**
     * 验证是否为 e-mail
     */
    public static final int EMAIL = 4;

//    public static void main(String[] args) {
//        System.out.println(validate("2r", INT));
//    }

    /**
     * 对字符串进行验证
     *
     * @param input   需要验证的字符串
     * @param matcher 验证规则
     * @return 验证是否通过
     */
    public static boolean validate(String input, int matcher) {
    if (isNULL(input)) {
    return false;
}
String regex = null;
switch (matcher) {
    case INT:
        regex = P_INT;
        break;
    case FLOAT:
        regex = P_FLOAT;
        break;
    case PHONE:
        regex = P_PHONE;
        break;
    case EMAIL:
        regex = P_EMAIL;
        break;
    default:
        return false;
}
return Pattern.matches(regex, input);
}

public static String toString(Object value) {
    if (isNULL(value))
        return "";
    else
        return value.toString().trim();
}

/**
 * 转换数据位字符串，当字符串为空时返回默认值
 *
 * @param value
 * @param defaultValue
 * @return
 */
public static String toString(Object value, String defaultValue) {
    if (isNULL(value))
        return defaultValue;
    else
        return value.toString().trim();
}

/**
 * 检查字符串是否是空白：<code>null</code>、空字符串<code>""</code>或只有空白字符。
 * <p>
 * <pre>
 * StringUtil.isBlank(null)      = true
 * StringUtil.isBlank("")        = true
 * StringUtil.isBlank(" ")       = true
 * StringUtil.isBlank("bob")     = false
 * StringUtil.isBlank("  bob  ") = false
 * </pre>
 *
 * @return 如果为空白, 则返回<code>true</code>
 */
public static boolean isBlank(Object obj) {
    if (isNULL(obj)) {
        return true;
    }
    String str = toString(obj);
    int length;

    if ((str == null) || ((length = str.length()) == 0)) {
        return true;
    }

    for (int i = 0; i < length; i++) {
        if (!Character.isWhitespace(str.charAt(i))) {
            return false;
        }
    }

    return true;
}

/**
 * 检查字符串是否不是空白：<code>null</code>、空字符串<code>""</code>或只有空白字符。
 * <p>
 * <pre>
 * Validation.isBlank(null)      = false
 * Validation.isBlank("")        = false
 * Validation.isBlank(" ")       = false
 * Validation.isBlank("bob")     = true
 * Validation.isBlank("  bob  ") = true
 * </pre>
 *
 * @param str 要检查的字符串
 * @return 如果为空白, 则返回<code>true</code>
 */
public static boolean isNotBlank(String str) {
    int length;

    if ((str == null) || ((length = str.length()) == 0)) {
        return false;
    }

    for (int i = 0; i < length; i++) {
        if (!Character.isWhitespace(str.charAt(i))) {
            return true;
        }
    }

    return false;
}

/**
 * 检查字符串是否为<code>null</code>或空字符串<code>""</code>。
 * <p>
 * <pre>
 * Validation.isEmpty(null)      = true
 * Validation.isEmpty("")        = true
 * Validation.isEmpty(" ")       = false
 * Validation.isEmpty("bob")     = false
 * Validation.isEmpty("  bob  ") = false
 * </pre>
 *
 * @param str 要检查的字符串
 * @return 如果为空, 则返回<code>true</code>
 */
public static boolean isEmpty(String str) {
    return ((str == null) || (str.length() == 0));
}

/**
 * 检查字符串是否不是<code>null</code>和空字符串<code>""</code>。
 * <p>
 * <pre>
 * Validation.isEmpty(null)      = false
 * Validation.isEmpty("")        = false
 * Validation.isEmpty(" ")       = true
 * Validation.isEmpty("bob")     = true
 * Validation.isEmpty("  bob  ") = true
 * </pre>
 *
 * @param str 要检查的字符串
 * @return 如果不为空, 则返回<code>true</code>
 */
public static boolean isNotEmpty(String str) {
    return ((str != null) && (str.length() > 0));
}

public static String isNbsp(Object value) {
    if (isNULL(value)) {
        return "&nbsp;";
    } else {
        return value.toString();
    }
}

public static Boolean isNULL(Object... value) {
    if (null == value || value.length < 1) {
        return true;
    } else {
        for (int i = 0; i < value.length; i++) {
            if (null == value[i] || "".equals(value[i].toString()) || "null".equals(value[i].toString().toLowerCase())) {
                return true;
            }
        }
    }
    return false;
}

public static <T> boolean isNotNUllToList(List<T> list) {
    if (list != null && list.size() > 0) {
        return true;
    }
    return false;
}

public static <T> boolean isNotNUllToSet(Set<T> set) {
    if (set != null && set.size() > 0) {
        return true;
    }
    return false;
}

public static <K, V> boolean isNotNullToMap(Map<K, V> map) {
    return map != null && map.size() > 0;
}

/**
 * @param format "" ##.## ,###.## ,000.00 0.00 ,### ,000
 * @param value  数字
 * @return
 */
public static String toNumber(String format, Object value) {
    if (isNULL(value)) {
        return "";
    }
    DecimalFormat df = new DecimalFormat(format);
    StringBuffer sb = new StringBuffer();
    df.format(new BigDecimal(value.toString()), sb, new FieldPosition(0));
    return sb.toString();
}

public static Boolean equals(Object o, Object x) {
    if (isNULL(o) || isNULL(x)) {
        return false;
    }

    if (o == x || o.equals(x) || o.toString().equals(x)) {
        return true;
    }
    return false;
}

public static Float toFloat(Object value) {
    if (isNULL(value))
        return 0.0f;
else
    return Float.parseFloat(value.toString());
}

public static Float toFloat(Object value, int decimal) {
    if (isNULL(value))
        return 0.0f;
else {
        float f = Float.parseFloat(value.toString());
        BigDecimal b = new BigDecimal(f);
        return b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
    }
}

public static BigDecimal toBigDecimal(Object value) {
    if (!isNumeric(value))
        return BigDecimal.valueOf(0.0);
    else
        return new BigDecimal(value.toString());
}

public static boolean isNumeric(Object value) {
    if (isNULL(value)) {
        return false;
    }
    Pattern pattern1 = Pattern.compile("^[-\\+]?[\\d]*$");
    Pattern pattern2 = Pattern.compile("^[-\\+]?[.\\d]*$");
    return pattern1.matcher(value.toString()).matches() || pattern2.matcher(value.toString()).matches();
}

public static boolean isInteger(Object value) {
    if (isNULL(value)) {
        return false;
    }
    Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
    return pattern.matcher(value.toString()).matches();
}

public static boolean isFolat(Object value) {
    if (isNULL(value)) {
        return false;
    }
    Pattern pattern = Pattern.compile("^[-\\+]?[.\\d]*$");
    return pattern.matcher(value.toString()).matches();
}

public static Boolean isBoolean(Object value) {
    if (isNULL(value))
        return false;
    else if (value.toString().equals("0") || value.toString().equalsIgnoreCase("false"))
        return false;
    else
        return true;
}

public static Integer toInteger(Object value) {
    if (isNULL(value) || !isNumeric(value))
        return 0;
    else
        return Integer.parseInt(value.toString());
}

public static Integer toInteger(Object value, int defaultValue) {
    if (isNULL(value) || !isNumeric(value))
        return defaultValue;
    else
        return Integer.parseInt(value.toString());
}

public static Integer[] toIntegerArr(Object[] value) {
    int size = value.length;
    Integer[] intArr = new Integer[size];
    for (int i = 0; i < value.length; i++) {
        intArr[i] = toInteger(value[i]);
    }
    return intArr;
}

public static Long toLong(Object value) {
    if (isNULL(value) || !isNumeric(value)) {
        return 0l;
    } else
        return Long.parseLong(value.toString());
}

public static Long toLong(Object value, long defaultValue) {
    if (isNULL(value) || !isNumeric(value)) {
        return defaultValue;
    } else
        return Long.parseLong(value.toString());
}

public static Double toDouble(Object value) {
    if (isNULL(value) || !isNumeric(value)) {
        return 0.0d;
    } else {
        return Double.parseDouble(value.toString());
    }
}

public static Double toDouble(Object value, double defaultValue) {
    if (isNULL(value) || !isNumeric(value)) {
        return defaultValue;
    } else {
        return Double.parseDouble(value.toString());
    }
}

public static Boolean toBoolean(Object value) {
    if (isNULL(value))
        return false;
    else
        return Boolean.parseBoolean(value.toString());
}

public static Boolean toBoolean(Object value, boolean defaultValue) {
    if (isNULL(value))
        return defaultValue;
    else
        return Boolean.parseBoolean(value.toString());
}

/**
 * 取消字符串中的空字符
 *
 * @return 取消了空字符的字符串
 */
public static String toTrim(String str) {
    String s = "";
    if (null != str) {
        s = str.trim();
    }
    return s;
}

/**
 * 检查字符串是否为日期
 *
 * @author RASCAL
 */
public static boolean isDate(Object value) {
    return isDateFormat(value, "^\\d{4}-\\d{1,2}-\\d{1,2}$");
}

/**
 * 检查字符串是否为日期时间类型
 *
 * @author RASCAL
 */
public static boolean isDateTime(Object value) {
    return isDateFormat(value, "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}$");
}

/**
 * 检查字符串是否为指定的日期类型
 *
 * @param fromatReg 格式正则表达式
 * @author RASCAL
 */
public static boolean isDateFormat(Object value, String fromatReg) {
    Pattern pattern = Pattern.compile(fromatReg);
    if (isNULL(value) || !pattern.matcher(value.toString()).matches()) {
        return false;
    } else {
        return true;
    }
}

/**
 * 解析日期类型，格式"yyyyMMdd"
 *
 * @author RASCAL
 */
public static Date toDate(Object value) {
    return parseDate(value, "yyyy-MM-dd");
}

/**
 * 解析日期时间类型，格式"yyyyMMdd HH:mm:ss"
 *
 * @author RASCAL
 */
public static Date toDateTime(Object value) {
    return parseDate(value, "yyyy-MM-dd HH:mm:ss");
}

/**
 * 解析日期类型
 *
 * @param fromat 日期格式
 * @author RASCAL
 */
public static Date parseDate(Object value, String fromat) {
    // 解析日期格式
    try {
        DateFormat df = new SimpleDateFormat(fromat);
        return df.parse(value.toString());
    } catch (Exception ex) {
//            LOGGER.warn(LogUtil.getLogStr("TimeConvertException", "500", ex.getMessage()));
    }
    return null;
}

/**
 * @param format 日期格式
 * @return 当日日期
 * @author RASCAL
 */
public static String toDay(String format) {
    if (isNULL(format)) {
        return new SimpleDateFormat(format).format(new Date());
    } else {
        return new SimpleDateFormat(format).format(new Date());
    }
}

// 生成 32 位的 id
public static String getCode(int mnum) {
    char strRandom[] = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
        'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    // 建立字符串数组
    StringBuffer finalStr = new StringBuffer();
    for (int count = 0; count < mnum; count++) {
        int randomNum = new Random().nextInt(62);
        // 随机字符串数组中字符的位置
        finalStr.append(strRandom[randomNum]);
    }
    return finalStr.toString();
}

public static String[][] toArray(String x) {
    String[] xx = x.split("=!=");
    String[][] xxx = new String[xx.length][];
    for (int i = 0; i < xx.length; i++) {
        xxx[i] = xx[i].split("-!-");
    }
    return xxx;
}

public static Object[] toByte(Object value) {
    return null;
}

public static Object[] toTime(Object value) {
    return null;
}

public static boolean isIntegerRegex(String str) {
    if (isNULL(str)) {
        return false;
    }
    return str.matches("^[0-9]+$");
}

/**
 * 检查字符串是否为日期
 *
 * @author RASCAL
 */
public static boolean isTime(Object value) {
    return isDateFormat(value, "^\\d{4}-\\d{1,2}-\\d{1,2}$") || isDateFormat(value, "^\\d{4}-\\d{1,2}$") || isDateFormat(value, "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}$")
        || isDateFormat(value, "^\\d{2}:\\d{2}:\\d{2}$");
}
}
