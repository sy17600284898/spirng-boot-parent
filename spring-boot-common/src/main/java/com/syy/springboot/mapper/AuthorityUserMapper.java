package com.syy.springboot.mapper;

import com.syy.springboot.model.AuthorityUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * UserMapper继承基类
 */
@Mapper
public interface AuthorityUserMapper extends MyBatisBaseMapper<AuthorityUser, Integer> {

    /**
     * 通过登录名获取帐号信息
     *
     * @param name
     * @return
     */
    AuthorityUser getByName(String name);

    /**
     * getDataAll
     *
     * @return
     */
    List<AuthorityUser> getDataAll();

    /**
     * getAuthorityUser
     * @return
     */
    List<AuthorityUser> getAuthorityUser();
}