package com.aliexpress.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.util.UUID;

public class Util {
    private static BASE64Encoder encoder = new BASE64Encoder();// 加密
    private static BASE64Decoder decoder = new BASE64Decoder();// 解密
    /**
     * 获得指定数目的UUID
     *
     * @param number int 需要获得的UUID数量
     * @return String[] UUID数组
     */
    public static String[] getUUID(int number) {
    if (number < 1) {
    return null;
}
String[] retArray = new String[number];
for (int i = 0; i < number; i++) {
    retArray[i] = getUUID();
}
return retArray;
}

/**
 * 获得一个UUID
 *
 * @return String UUID
 */
public static String getUUID() {
    String uuid = UUID.randomUUID().toString();
    //去掉“-”符号
    return uuid.replaceAll("-", "");
}


/**
 * 加密
 *
 * @param
 * @return
 * @throws Exception
 */
public static String encryptBASE64(String inputStr) {
    String value = "";
    try {
        byte[] key = inputStr.getBytes();
        value = new String(encoder.encodeBuffer(key));
    } catch (Exception e) {
        e.printStackTrace();
    }
    return value.substring(0,value.length()-1);
}

/**
 * 解密
 *
 * @param
 * @return
 * @throws Exception
 */
public static String decryptBASE64(String outputStr) {
    String value = "";
    try {
        byte[] key = decoder.decodeBuffer(outputStr);
        value = new String(key);
    } catch (Exception e) {
    }
    return value;
}
}
