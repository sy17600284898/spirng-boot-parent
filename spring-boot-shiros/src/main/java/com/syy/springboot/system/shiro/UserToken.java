package com.syy.springboot.system.shiro;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.shiro.authc.UsernamePasswordToken;

import java.io.Serializable;

/**
 * UserToken
 *
 * @author: shiyan
 * @version: 2019-12-06 15:54
 * @Copyright: 2019 www.lenovo.com Inc. All rights reserved.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserToken extends UsernamePasswordToken implements Serializable {
    private static final long serialVersionUID = 4812793519945855483L;
    /**
     * 登录方式
     */

    private LoginType loginType;
    /**
     * 微信code
     */
    private String code;

    // TODO 由于是demo方法，此处微信只传一个code参数，其他参数根据实际情况添加


    public UserToken(LoginType loginType, final String username, final String password) {
        super(username, password);
        this.loginType = loginType;
    }

    public UserToken(LoginType loginType, String username, String password, String code) {
        super(username, password);
        this.loginType = loginType;
        this.code = code;
    }

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
