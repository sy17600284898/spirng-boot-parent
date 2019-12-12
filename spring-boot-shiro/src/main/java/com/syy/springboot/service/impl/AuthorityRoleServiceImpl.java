package com.syy.springboot.service.impl;

import com.syy.springboot.mapper.AuthorityRoleMapper;
import com.syy.springboot.mapper.AuthorityRolePermissionMapper;
import com.syy.springboot.mapper.AuthorityUserRoleMapper;
import com.syy.springboot.model.AuthorityRole;
import com.syy.springboot.model.AuthorityRolePermission;
import com.syy.springboot.model.AuthorityRoleVo;
import com.syy.springboot.model.AuthorityUserRole;
import com.syy.springboot.result.BasePageNewResponse;
import com.syy.springboot.service.AuthorityRoleService;
import com.syy.springboot.util.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author ASUS
 */
@Service
public class AuthorityRoleServiceImpl implements AuthorityRoleService {
    @Autowired
    private AuthorityRoleMapper roleMapper;
    @Autowired
    private AuthorityRolePermissionMapper authorityRolePermissionMapper;
    @Autowired
    private AuthorityUserRoleMapper authorityUserRoleMapper;

    @Override
    public boolean operatingAuthorityRole(AuthorityRole authorityRole) {
        if (authorityRole.getId() > 0) {
            roleMapper.updateByPrimaryKeySelective(authorityRole);
        }
        return roleMapper.insertSelective(authorityRole);
    }

    @Override
    public AuthorityRole queryByRoleId(int id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    @Override
    public void operatingRolePermission(List<AuthorityRolePermission> list, int id) {
        authorityRolePermissionMapper.deleteByRole(id);
        authorityRolePermissionMapper.insertRolePermission(list);
    }

    @Override
    public BasePageNewResponse getRoleList(AuthorityRole toEntity, Page toPage) {
        BasePageNewResponse basePageNewResponse = new BasePageNewResponse();
        Map<String, Object> map = new HashMap<>(16);
        map.put("begin", toPage.getBegin());
        map.put("length", toPage.getLength());
        basePageNewResponse.setPageIndex(toPage.getBegin());
        basePageNewResponse.setPageSize(toPage.getLength());
        int count = roleMapper.count(map);
        basePageNewResponse.setPageNum(count);
        List<AuthorityRole> authorityUserList = roleMapper.selectByExample(map);
        List<AuthorityRoleVo> authorityUserVoList = getResultData(authorityUserList);
        basePageNewResponse.setData(authorityUserVoList);
        return basePageNewResponse;
    }

    @Override
    public List<AuthorityUserRole> queryByUserId(int roleId) {
        return authorityUserRoleMapper.queryByUserId(roleId);
    }

    private List<AuthorityRoleVo> getResultData(List<AuthorityRole> authorityUserList) {
        List<AuthorityRoleVo> authorityUserVoList = new ArrayList<>();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (AuthorityRole authorityRole : authorityUserList) {
            AuthorityRoleVo authorityUserVo = new AuthorityRoleVo();
            BeanUtils.copyProperties(authorityRole, authorityUserVo);
            authorityUserVo.setCreateDate(sf.format(authorityUserVo.getCreateDate()));
            authorityUserVo.setUpdateDate(sf.format(authorityUserVo.getUpdateDate()));
            authorityUserVoList.add(authorityUserVo);
        }
        return authorityUserVoList;
    }
}
