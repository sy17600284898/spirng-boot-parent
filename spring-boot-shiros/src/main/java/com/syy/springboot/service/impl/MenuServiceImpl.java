package com.syy.springboot.service.impl;

import com.syy.springboot.mapper.MenuMapper;
import com.syy.springboot.model.Menu;
import com.syy.springboot.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> getAllMenuByRoleId(Integer roleId){
        return menuMapper.getAllMenuByRoleId(roleId);
    }
}
