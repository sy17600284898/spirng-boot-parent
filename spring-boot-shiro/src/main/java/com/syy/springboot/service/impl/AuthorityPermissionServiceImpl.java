package com.syy.springboot.service.impl;


import com.syy.springboot.mapper.AuthorityPermissionMapper;
import com.syy.springboot.model.AuthorityPermission;
import com.syy.springboot.result.BaseResponse;
import com.syy.springboot.result.ResultStatusCode;
import com.syy.springboot.service.AuthorityPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ASUS
 */
@Service
public class AuthorityPermissionServiceImpl implements AuthorityPermissionService {
    @Autowired
    private AuthorityPermissionMapper permissionMapper;


    @Override
    public List<AuthorityPermission> getPermissionList() {
        return permissionMapper.findAll();
    }

    @Override
    public BaseResponse<Object> operatingAuthorityPermission(AuthorityPermission authorityPermission) {
        if (authorityPermission.getId() > 0) {
            permissionMapper.insertSelective(authorityPermission);
            return new BaseResponse(true, ResultStatusCode.OK);
        }
        permissionMapper.updateByPrimaryKeySelective(authorityPermission);
        return new BaseResponse(true, ResultStatusCode.OK);
    }

    @Override
    public AuthorityPermission queryByPermissionId(int id) {
        return permissionMapper.selectByPrimaryKey(id);
    }

    @Override
    public BaseResponse<Object> deletePermissionById(int id) {
        return permissionMapper.updateByid(id);
    }
}
