package com.syy.springboot.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.syy.springboot.result.Grid;
import com.syy.springboot.mapper.UserMapper;
import com.syy.springboot.model.User;
import com.syy.springboot.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author ASUS
 */
@Service
public class UserServiceimpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public Grid findList() {
        Grid grid = new Grid();

        Page page = PageHelper.offsetPage(0, 10, true);
        userMapper.findList(null);

        grid.setRows(page.getResult());
        grid.setTotal(page.getTotal());

        return grid;
    }

    @Override
    public User getUserByName(String name) {
        return userMapper.getByName(name);
    }


    @Override
    public User getByOpenid(String openid) {
        //这里假装是通过openid从数据库中获取到用户信息
        User user = new User();
        user.setName("张三");
        user.setLoginName("zhangsan");
        user.setPhone("18888888888");
        user.setId(1);
        user.setLoginFlag("1");

        return user;
    }

    @Override
    public User getByPhone(String phone) {
        return userMapper.getByPhone(phone);
    }
}
