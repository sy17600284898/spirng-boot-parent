package com.syy.springboot.util;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

/**
 * MD5Util
 *
 * @author: shiyan
 * @version: 2019-12-06 15:36
 * @Copyright: 2019 www.lenovo.com Inc. All rights reserved.
 */
public class MD5Util {
    public static String encrypt(String encryptStr) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] md5Bytes = md5.digest(encryptStr.getBytes());
            StringBuffer hexValue = new StringBuffer();
            for (int i = 0; i < md5Bytes.length; i++) {
                int val = ((int) md5Bytes[i]) & 0xff;
                if (val < 16)
                    hexValue.append("0");
                hexValue.append(Integer.toHexString(val));
            }
            encryptStr = hexValue.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return encryptStr;
    }

    public static String encrypt16(String encryptStr) {
        return encrypt(encryptStr).substring(8, 24);
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        String str = "hello Base64按时大大爱仕达啊实打实大的sadsad阿萨德阿达大打奥德赛 啊啊\n" +
                "ADCDEFG";
        //编码加密
        String encodeStr = Base64.getEncoder().encodeToString(str.getBytes("UTF-8"));
        System.out.println("加密后的字符串为:" + encodeStr);

        //解码解密
        String decoderStr = new String(Base64.getDecoder().decode(encodeStr), StandardCharsets.UTF_8); //
        // 推荐使用StandardCharsets类指定
        System.out.println("解密后的字符串为" + decoderStr);
//        String s = encrypt("666666");
//        System.out.println(s);
    }
}
