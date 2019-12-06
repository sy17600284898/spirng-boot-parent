package com.syy.springboot.service;

import com.syy.springboot.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> findByUserid(Integer userId);
}
