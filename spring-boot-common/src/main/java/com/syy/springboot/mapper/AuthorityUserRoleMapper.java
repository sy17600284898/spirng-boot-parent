package com.syy.springboot.mapper;


import com.syy.springboot.model.AuthorityUserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * UserRoleMapper继承基类
 */
@Mapper
public interface AuthorityUserRoleMapper extends MyBatisBaseMapper<AuthorityUserRole, AuthorityUserRole> {
    /**
     * insertAuthorityUserRole
     *
     * @param list
     * @param list
     * @return
     */
    boolean insertAuthorityUserRole(List<AuthorityUserRole> list);

    /**
     * deleteByUserId
     *
     * @param userId
     */
    void deleteByUserId(Integer userId);

    /**
     * queryByUserId
     *
     * @param roleId
     * @return
     */
    List<AuthorityUserRole> queryByUserId(int roleId);
}