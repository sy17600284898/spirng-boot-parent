package com.syy.springboot.mapper;


import com.syy.springboot.model.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * MenuDAO继承基类
 * @author ASUS
 */
@Mapper
public interface MenuMapper extends MyBatisBaseMapper<Menu, Integer> {

    /**
     * 通过角色id获取所有权限信息
     * @param roleId
     * @return
     */
    List<Menu> getAllMenuByRoleId(Integer roleId);
}