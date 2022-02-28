package erha.fun.demo.utils;

import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Random;

/**
 * @author Ackerven
 * @version 1.0
 * Copyright (c) 2022 Ackerven All rights reserved.
 * @date 2/28/22 9:46 PM
 */

public class Tools {
    /**
     * 获取随机字符串
     * @param length 字符串长度
     * @return 随机字符串
     */
    public static String randomString(int length) {
        String str = "123456789abcedfghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        return getString(length, str);
    }

    /**
     * 获取随机字符串
     * @param length 字符串长度
     * @param str 随机字符串包含的字符
     * @return 随机字符串
     */
    public static String randomString(int length, String str) {
        return getString(length, str);
    }

    /**
     * 生成随机字符串
     * @param length 字符串长度
     * @param str 随机字符串包含的字符
     * @return 随机字符串
     */
    private static String getString(int length, String str) {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < length; i++) {
            int index = random.nextInt(str.length());
            stringBuilder.append(str.charAt(index));
        }
        return stringBuilder.toString();
    }

    /**
     * MD5 加密
     * @param str 需要加密的字符串
     * @return 加密后的数据
     */
    public static String encryptToMD5(String str) {
        return DigestUtils.md5DigestAsHex(str.getBytes(StandardCharsets.UTF_8));
    }
}
