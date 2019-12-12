package com.syy.springboot.service.impl;


import com.syy.springboot.mapper.AuthorityUserMapper;
import com.syy.springboot.mapper.AuthorityUserRoleMapper;
import com.syy.springboot.model.AuthorityUser;
import com.syy.springboot.model.AuthorityUserRole;
import com.syy.springboot.model.AuthorityUserVo;
import com.syy.springboot.result.BasePageNewResponse;
import com.syy.springboot.service.AuthorityUserService;
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
public class AuthorityUserServiceimpl implements AuthorityUserService {
    @Autowired
    private AuthorityUserMapper userMapper;

    @Autowired
    private AuthorityUserRoleMapper authorityUserRoleMapper;

    @Override
    public boolean operatingAuthorityUserRole(List<AuthorityUserRole> list, int uid) {
        authorityUserRoleMapper.deleteByUserId(uid);
        return authorityUserRoleMapper.insertAuthorityUserRole(list);
    }

    @Override
    public AuthorityUser queryByUid(int id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean operatingAuthorityUser(AuthorityUser authorityUser) {
        if (authorityUser.getId() > 0) {
            userMapper.updateByPrimaryKeySelective(authorityUser);
        }
        return userMapper.insertSelective(authorityUser);
    }

    @Override
    public BasePageNewResponse getUserList(AuthorityUser toEntity, Page toPage) {
        BasePageNewResponse basePageNewResponse = new BasePageNewResponse();
        Map<String, Object> map = new HashMap<>(16);
        map.put("begin", toPage.getBegin());
        map.put("length", toPage.getLength());
        basePageNewResponse.setPageIndex(toPage.getBegin());
        basePageNewResponse.setPageSize(toPage.getLength());
        int count = userMapper.count(map);
        basePageNewResponse.setPageNum(count);
        List<AuthorityUser> authorityUserList = userMapper.selectByExample(map);
        List<AuthorityUserVo> authorityUserVos = getResultData(authorityUserList);
        basePageNewResponse.setData(authorityUserVos);
        return basePageNewResponse;
    }

    @Override
    public List<AuthorityUser> getDataAll() {
        return userMapper.getDataAll();
    }

    private List<AuthorityUserVo> getResultData(List<AuthorityUser> authorityUserList) {
        List<AuthorityUserVo> authorityUserVoList = new ArrayList<>();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (AuthorityUserVo authorityUserVo : authorityUserVoList) {
            AuthorityUserVo authorityUserVos = new AuthorityUserVo();
            BeanUtils.copyProperties(authorityUserVo, authorityUserVos);
            authorityUserVos.setCreateDate(sf.format(authorityUserVo.getCreateDate()));
            authorityUserVos.setUpdateDate(sf.format(authorityUserVo.getUpdateDate()));
            authorityUserVoList.add(authorityUserVos);
        }
        return authorityUserVoList;
    }
}
