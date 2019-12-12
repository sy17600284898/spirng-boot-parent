package com.syy.springboot.shiroconfig;

import com.syy.springboot.enums.LoginType;
import org.apache.shiro.authc.UsernamePasswordToken;

import java.io.Serializable;

/**
 * UserToken
 *
 * @author: shiyan
 * @version: 2019-12-011 15:54
 * @Copyright: 2019 www.lenovo.com Inc. All rights reserved.
 */
public class UserToken extends UsernamePasswordToken implements Serializable {
    private static final long serialVersionUID = 4812793519945855483L;
    /**
     * 登录方式
     */
    private LoginType loginType;

    public UserToken(LoginType loginType, final String username, final String password) {
        super(username, password);
        this.loginType = loginType;
    }

    public LoginType getLoginType() {
        return loginType;
    }
}
