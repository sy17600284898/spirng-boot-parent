package com.syy.springboot.service;

import com.syy.springboot.model.Menu;

import java.util.List;


public interface MenuService {

     List<Menu> getAllMenuByRoleId(Integer roleId);
}
