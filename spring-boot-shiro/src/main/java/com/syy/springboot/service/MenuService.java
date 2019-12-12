package com.syy.springboot.service;

import com.syy.springboot.model.Menu;

import java.util.List;


/**
 * @author ASUS
 */
public interface MenuService {

    /**
     * getAllMenuByRoleId
     *
     * @param roleId
     * @return
     */
    List<Menu> getAllMenuByRoleId(Integer roleId);
}
