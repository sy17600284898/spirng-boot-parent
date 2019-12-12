package com.syy.springboot.model;


import java.io.Serializable;

/**
 * RoleMenu
 *
 * @author: shiyan
 * @version: 2019-12-06 15:34
 * @Copyright: 2019 www.lenovo.com Inc. All rights reserved.
 */

public class AuthorityRolePermission implements Serializable {
    /**
     * 角色编号
     */
    private Integer roleId;

    /**
     * 菜单编号
     */
    private Integer permissionId;

    private static final long serialVersionUID = 1L;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }
}
