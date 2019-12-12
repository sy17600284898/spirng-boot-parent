package com.syy.springboot.model;


import java.io.Serializable;

/**
 * UserRole
 *
 * @author: shiyan
 * @version: 2019-12-06 15:34
 * @Copyright: 2019 www.lenovo.com Inc. All rights reserved.
 */
public class AuthorityUserRole implements Serializable {
    /**
     * 用户编号
     */
    private Integer userId;

    /**
     * 角色编号
     */
    private Integer roleId;

    private static final long serialVersionUID = 1L;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
