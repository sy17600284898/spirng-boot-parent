package com.syy.springboot.config.shiro;

import com.syy.springboot.model.Menu;
import com.syy.springboot.model.Role;
import com.syy.springboot.model.User;
import com.syy.springboot.service.MenuService;
import com.syy.springboot.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * AuthorizationRealm
 *
 * @author: shiyan
 * @version: 2019-12-06 15:49
 * @Copyright: 2019 www.lenovo.com Inc. All rights reserved.
 * 统一角色授权控制策略
 */
@Slf4j
public class AuthorizationRealm extends AuthorizingRealm {
    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;

    /**
     * 授权
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.info("---------------- 执行 Shiro 权限获取 ---------------------");
        Object principal = principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        if (principal instanceof User) {
            User userLogin = (User) principal;
            if (userLogin != null) {
                List<Role> roleList = roleService.findByUserid(userLogin.getId());
                if (CollectionUtils.isNotEmpty(roleList)) {
                    for (Role role : roleList) {
                        info.addRole(role.getEnname());

                        List<Menu> menuList = menuService.getAllMenuByRoleId(role.getId());
                        if (CollectionUtils.isNotEmpty(menuList)) {
                            for (Menu menu : menuList) {
                                if (StringUtils.isNoneBlank(menu.getPermission())) {
                                    info.addStringPermission(menu.getPermission());
                                }
                            }
                        }
                    }
                }
            }
        }
        log.info("---------------- 获取到以下权限 ----------------");
        log.info(info.getStringPermissions().toString());
        log.info("---------------- Shiro 权限获取成功 ----------------------");
        return info;
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
        return null;
    }
}
