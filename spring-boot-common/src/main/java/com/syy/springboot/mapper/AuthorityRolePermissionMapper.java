package com.syy.springboot.mapper;

import com.syy.springboot.model.AuthorityRolePermission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * RoleMenuMapper继承基类
 */
@Mapper
public interface AuthorityRolePermissionMapper extends MyBatisBaseMapper<AuthorityRolePermission, AuthorityRolePermission> {
    /**
     * deleteByRole
     *
     * @param roleId
     */
    void deleteByRole(int roleId);

    /**
     * insertRolePermission
     *
     * @param list
     */
    void insertRolePermission(List<AuthorityRolePermission> list);
}