package com.syy.springboot.model;

import java.util.List;

/**
 * AuthorityUserVo
 *
 * @author: shiyan
 * @version: 2019-12-11 10:39
 * @Copyright: 2019 www.lenovo.com Inc. All rights reserved.
 */
public class AuthorityUserVo {
    /**
     * 编号
     */
    private Integer id;

    /**
     * 登录名
     */
    private String loginName;

    /**
     * 密码
     */
    private String password;

    /**
     * 角色id
     */
    private Integer roleId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 邮箱
     */
    private String email;
    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    private String createDate;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private String updateDate;

    /**
     * 删除标记
     */
    private boolean delFlag;

    //令牌
    private String token;
    //加密密码的盐
    private String salt = "8d78869f470951332959580424d4bf4f";

    private List<AuthorityRole> roleList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public boolean isDelFlag() {
        return delFlag;
    }

    public void setDelFlag(boolean delFlag) {
        this.delFlag = delFlag;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<AuthorityRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<AuthorityRole> roleList) {
        this.roleList = roleList;
    }

    /**
     * 密码盐.
     *
     * @return
     */
    public String getCredentialsSalt() {
        return this.loginName + this.salt;
    }


}
