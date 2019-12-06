package com.syy.springboot.mapper;

import com.syy.springboot.model.RoleMenu;
import org.apache.ibatis.annotations.Mapper;

/**
 * RoleMenuDAO继承基类
 */
@Mapper
public interface RoleMenuMapper extends MyBatisBaseMapper<RoleMenu, RoleMenu> {
}