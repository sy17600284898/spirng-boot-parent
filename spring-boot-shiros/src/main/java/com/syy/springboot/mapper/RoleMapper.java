package com.syy.springboot.mapper;

import com.syy.springboot.model.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * RoleMapper继承基类
 */
@Mapper
public interface RoleMapper extends MyBatisBaseMapper<Role, Integer> {

    /**
     * 通过用户id获取用户拥有的角色
     *
     * @param userId
     * @return
     */
    List<Role> findByUserId(Integer userId);
}