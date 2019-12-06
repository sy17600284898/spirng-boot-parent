package com.syy.springboot.mapper;


import com.syy.springboot.model.UserRole;
import org.apache.ibatis.annotations.Mapper;

/**
 * UserRoleDAO继承基类
 */
@Mapper
public interface UserRoleMapper extends MyBatisBaseMapper<UserRole, UserRole> {
}