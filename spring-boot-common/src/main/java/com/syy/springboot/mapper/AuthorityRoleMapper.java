package com.syy.springboot.mapper;

import com.syy.springboot.model.AuthorityRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * RoleMapper继承基类
 */
@Mapper
public interface AuthorityRoleMapper extends MyBatisBaseMapper<AuthorityRole, Integer> {

    /**
     * 通过用户id获取用户拥有的角色
     *
     * @param userId
     * @return
     */
    List<AuthorityRole> findByUserId(Integer userId);
}