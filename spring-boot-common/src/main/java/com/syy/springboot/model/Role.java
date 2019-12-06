package com.syy.springboot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * Role
 *
 * @author: shiyan
 * @version: 2019-12-06 15:33
 * @Copyright: 2019 www.lenovo.com Inc. All rights reserved.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Role implements Serializable {
    /**
     * 编号
     */
    private Integer id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 英文名称
     */
    private String enname;

    /**
     * 数据范围
     */
    private String dataScope;

    /**
     * 角色类型
     */
    private Integer roleType;

    /**
     * 是否系统数据
     */
    private String isSys;

    /**
     * 是否可用
     */
    private Integer useable;

    /**
     * 创建者
     */
    private Integer createBy;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 更新者
     */
    private Integer updateBy;

    /**
     * 更新时间
     */
    private Date updateDate;

    /**
     * 备注信息
     */
    private String remarks;

    /**
     * 删除标记
     */
    private String delFlag;

    private static final long serialVersionUID = 1L;
}
