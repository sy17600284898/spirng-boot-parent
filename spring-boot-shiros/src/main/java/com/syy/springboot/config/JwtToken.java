package com.syy.springboot.config;

import javax.xml.crypto.Data;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JwtToken
 *
 * @author: shiyan
 * @version: 2019-12-09 11:37
 * @Copyright: 2019 www.lenovo.com Inc. All rights reserved.
 */
public class JwtToken {
    /**
     * 公用秘钥，保存在服务器，客户端是不会知道的，防止攻击
     */
    public static String SECRET = "FreeMaMong";


    public static String createToken() {
        //签发时间
        Date iatDate = new Date();
        //过期时间 -1分钟过期
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.MINUTE, 1);
        Date expiresDate = nowTime.getTime();
        Map<String, Object> map = new HashMap<>();
        return null;
    }
}
