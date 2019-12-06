package com.syy.springboot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * Menu
 *
 * @author: shiyan
 * @version: 2019-12-06 15:33
 * @Copyright: 2019 www.lenovo.com Inc. All rights reserved.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menu  implements Serializable {
    /**
     * 编号
     */
    private Integer id;

    /**
     * 父级编号
     */
    private Integer parentId;

    /**
     * 所有父级编号
     */
    private String parentIds;

    /**
     * 名称
     */
    private String name;

    /**
     * 排序
     */
    private Long sort;

    /**
     * 链接
     */
    private String href;

    /**
     * 目标
     */
    private String target;

    /**
     * 图标
     */
    private String icon;

    /**
     * 是否在菜单中显示
     */
    private String isShow;

    /**
     * 权限标识
     */
    private String permission;

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
}
