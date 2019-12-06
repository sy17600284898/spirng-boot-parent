package com.syy.springboot.mapper;

import com.syy.springboot.model.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * UserDAO继承基类
 */
@Mapper
public interface UserMapper extends MyBatisBaseMapper<User, Integer> {

    /**
     * 通过登录名获取帐号信息
     *
     * @param name
     * @return
     */
    User getByName(String name);

    /**
     * 通过手机号获取帐号信息
     *
     * @param phone
     * @return
     */
    User getByPhone(String phone);
}