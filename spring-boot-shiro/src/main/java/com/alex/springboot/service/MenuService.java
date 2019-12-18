package com.alex.springboot.service;

import com.alex.springboot.dao.MenuDAO;
import com.alex.springboot.model.Menu;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MenuService {
    @Resource
    private MenuDAO menuDAO;

    public List<Menu> getAllMenuByRoleId(Integer roleId){
        return menuDAO.getAllMenuByRoleId(roleId);
    }
}
