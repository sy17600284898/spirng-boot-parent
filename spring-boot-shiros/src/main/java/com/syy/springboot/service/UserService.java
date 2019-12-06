package com.syy.springboot.service;

import com.syy.springboot.model.User;
import com.syy.springboot.system.vo.Grid;


public interface UserService {

    public Grid findList();

    public User getUserByName(String name);


    public User getByOpenid(String openid);

    public User getByPhone(String phone);
}
