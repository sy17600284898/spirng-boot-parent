package com.syy.springboot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * RoleMenu
 *
 * @author: shiyan
 * @version: 2019-12-06 15:34
 * @Copyright: 2019 www.lenovo.com Inc. All rights reserved.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoleMenu implements Serializable {
    /**
     * 角色编号
     */
    private Integer roleId;

    /**
     * 菜单编号
     */
    private Integer menuId;

    private static final long serialVersionUID = 1L;
}
