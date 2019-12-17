package com.syy.springboot.shiroconfig;

import com.syy.springboot.initialization.UserInits;
import com.syy.springboot.model.AuthorityPermission;
import com.syy.springboot.model.AuthorityRole;
import com.syy.springboot.model.AuthorityUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;

import java.util.Collection;
import java.util.Objects;

/**
 * UserPasswordRealm
 *
 * @author: shiyan
 * @version: 2019-12-06 15:53
 * @Copyright: 2019 www.lenovo.com Inc. All rights reserved.
 * 用户密码登录realm
 */
public class ShiroRealm extends AuthorizingRealm {

    /**
     * 重写方法,清除当前用户的的 授权缓存
     *
     * @param principals
     */
    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    /**
     * 重写方法，清除当前用户的 认证缓存
     *
     * @param principals
     */
    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    /**
     * 自定义方法：清除所有 授权缓存
     */
    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    /**
     * 自定义方法：清除所有 认证缓存
     */
    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    /**
     * 自定义方法：清除所有的  认证缓存  和 授权缓存
     */
    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }

    /**
     * 认证信息.(身份验证) : Authentication 是用来验证用户身份
     *
     * @param authcToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        String name = token.getUsername();
        DefaultWebSecurityManager securityManager = (DefaultWebSecurityManager) SecurityUtils.getSecurityManager();
        SessionManager sessionManager = (SessionManager) securityManager.getSessionManager();
        Collection<Session> sessions = sessionManager.getSessionDAO().getActiveSessions();
        for (Session session : sessions) {
            String currentLoginUser =
                    String.valueOf(session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY));
            if (name.equals(currentLoginUser)) {
                sessionManager.getSessionDAO().delete(session);
            }
        }
        AuthorityUser user = UserInits.getDataMap(name);
        if (Objects.isNull(user)) {
            return null;
        }
        // 用户为删除状态
        if (user.isDelFlag()) {
            throw new DisabledAccountException();
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user,
                user.getPassword(),
                ByteSource.Util.bytes(user.getCredentialsSalt()),
                getName()
        );
        return authenticationInfo;
    }

    /**
     * 授权
     * doGetAuthorizationInfo
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Object principal = principals.getPrimaryPrincipal();
        if (principal instanceof AuthorityUser) {
            AuthorityUser userLogin = (AuthorityUser) principal;
            if (Objects.nonNull(userLogin)) {
                for (AuthorityRole role : userLogin.getRoleList()) {
                    authorizationInfo.addRole(role.getRole());
                    for (AuthorityPermission p : role.getPermissionList()) {
                        authorizationInfo.addStringPermission(p.getPermission());
                    }
                }
            }
        }
        return authorizationInfo;
    }
}
