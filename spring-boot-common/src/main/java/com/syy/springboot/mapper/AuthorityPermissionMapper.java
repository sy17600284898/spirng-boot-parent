package com.syy.springboot.mapper;


import com.syy.springboot.model.AuthorityPermission;
import com.syy.springboot.result.BaseResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * MenuMapper继承基类
 * @author ASUS
 */
@Mapper
public interface AuthorityPermissionMapper extends MyBatisBaseMapper<AuthorityPermission, Integer> {

    /**
     * 通过角色id获取所有权限信息
     * @param roleId
     * @return
     */
    List<AuthorityPermission> getAllMenuByRoleId(Integer roleId);

    /**
     * findAll
     * @return
     */
    List<AuthorityPermission> findAll();

    /**
     * updateByid
     * @param id
     * @return
     */
    BaseResponse<Object> updateByid(int id);
}