package com.syy.springboot.model;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Role
 *
 * @author: shiyan
 * @version: 2019-12-06 15:33
 * @Copyright: 2019 www.lenovo.com Inc. All rights reserved.
 */
public class AuthorityRole implements Serializable {
    /**
     * 编号
     */
    private Integer id;

    /**
     *  角色标识程序中判断使用,如"admin",这个是唯一的:
     */
    private String role;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private Date updateDate;

    /**
     * 删除标记
     */
    private boolean delFlag;


    private List<AuthorityPermission> permissionList;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public boolean isDelFlag() {
        return delFlag;
    }

    public void setDelFlag(boolean delFlag) {
        this.delFlag = delFlag;
    }

    public List<AuthorityPermission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<AuthorityPermission> permissionList) {
        this.permissionList = permissionList;
    }
}
