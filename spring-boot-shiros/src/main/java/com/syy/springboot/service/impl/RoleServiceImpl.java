package com.syy.springboot.service.impl;

import com.syy.springboot.mapper.RoleMapper;
import com.syy.springboot.model.Role;
import com.syy.springboot.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> findByUserid(Integer userId){
        return roleMapper.findByUserId(userId);
    }
}
