package com.syy.springboot.config.shiro;

import com.syy.springboot.enums.LoginType;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;

import java.util.Collection;
import java.util.HashMap;

/**
 * MyModularRealmAuthenticator
 *
 * @author: shiyan
 * @version: 2019-12-06 15:51
 * @Copyright: 2019 www.lenovo.com Inc. All rights reserved.
 * 自定义多realm登录策略
 */
public class MyModularRealmAuthenticator extends ModularRealmAuthenticator {
    @Override
    protected AuthenticationInfo doAuthenticate(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 判断getRealms()是否返回为空
        assertRealmsConfigured();
        // 强制转换回自定义的UserNamePasswordToken
        UserToken token = (UserToken) authenticationToken;
        // 所有Realm
        Collection<Realm> realms = getRealms();
        // 登录类型对应的所有Realm
        HashMap<String, Realm> realmHashMap = new HashMap<>(realms.size());
        for (Realm realm : realms) {
            realmHashMap.put(realm.getName(), realm);
        }
        // 登录类型
        LoginType loginType = token.getLoginType();
        if (realmHashMap.get(loginType.getType()) != null) {
            return doSingleRealmAuthentication(realmHashMap.get(loginType.getType()), token);
        } else {
            return doMultiRealmAuthentication(realms, token);
        }
    }
}