package com.syy.springboot.service;

import com.syy.springboot.model.Role;

import java.util.List;

/**
 * @author ASUS
 */
public interface RoleService {
    List<Role> findByUserid(Integer userId);
}
