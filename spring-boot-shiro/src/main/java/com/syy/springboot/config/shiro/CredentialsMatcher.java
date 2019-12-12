package com.syy.springboot.config.shiro;

import com.syy.springboot.util.MD5Util;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * CredentialsMatcher
 *
 * @author: shiyan
 * @version: 2019-12-06 15:50
 * @Copyright: 2019 www.lenovo.com Inc. All rights reserved.
 */
public class CredentialsMatcher extends SimpleCredentialsMatcher {
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        UsernamePasswordToken utoken = (UsernamePasswordToken) token;
        // 获得用户输入的密码:(可以采用加盐(salt)的方式去检验)
        String inPassword = new String(utoken.getPassword());
        // 获得数据库中的密码
        String dbPassword = (String) info.getCredentials();
        // 进行密码的比对
        return this.equals(MD5Util.encrypt(inPassword), dbPassword);
    }
}
