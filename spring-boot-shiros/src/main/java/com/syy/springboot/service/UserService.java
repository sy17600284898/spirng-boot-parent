package com.syy.springboot.service;

import com.syy.springboot.model.User;
import com.syy.springboot.result.Grid;


/**
 * @author ASUS
 */
public interface UserService {

    Grid findList();

    User getUserByName(String name);

    User getByOpenid(String openid);

    User getByPhone(String phone);
}
