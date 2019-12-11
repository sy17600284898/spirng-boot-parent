package com.syy.springboot.base.service;

import com.syy.springboot.base.entity.UserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface UserInfoService {

    /**
     * 通过username查找用户信息;
     */
    UserInfo findByUsername(String username);

    UserInfo findByOne(Integer uid);

    /**
     * 更新用户信息
     */
    void updateUserInfo(UserInfo ui);


    Page<UserInfo> findByUserName(String username, Pageable pageable);

    Page<UserInfo> findAll(Pageable pageable);

    List<UserInfo> findAll();

    UserInfo save(UserInfo ui);

    UserInfo saveUser(UserInfo ui);

    int updateState(int state, String uid);

    int updatePassword(String password, Date updateDate, String uid);

    UserInfo saveRoleId(UserInfo ui);


}