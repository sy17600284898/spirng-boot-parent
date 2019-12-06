package com.syy.springboot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * UserRole
 *
 * @author: shiyan
 * @version: 2019-12-06 15:34
 * @Copyright: 2019 www.lenovo.com Inc. All rights reserved.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRole implements Serializable {
    /**
     * 用户编号
     */
    private Integer userId;

    /**
     * 角色编号
     */
    private Integer roleId;

    private static final long serialVersionUID = 1L;
}
