package com.syy.springboot.initialization;

import com.syy.springboot.mapper.AuthorityRoleMapper;
import com.syy.springboot.mapper.AuthorityUserMapper;
import com.syy.springboot.model.AuthorityRole;
import com.syy.springboot.model.AuthorityUser;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * UserInit
 *
 * @author: shiyan
 * @version: 2019-12-10 17:43
 * @Copyright: 2019 www.lenovo.com Inc. All rights reserved.
 */
public class UserInits {
    /**
     * 静态map对象
     */
    private static Map<String, AuthorityUser> dataMap = new HashMap<>(16);

    @Autowired
    private AuthorityUserMapper userMapper;
    @Autowired
    private AuthorityRoleMapper roleMapper;

    /**
     * 在构造方法执行后执行
     */
    @PostConstruct
    public void init() {
        initMap();
    }

    public void initMap() {
        List<AuthorityUser> applications = userMapper.getAuthorityUser();
        if (applications.size() > 0) {
            for (AuthorityUser authorityUser : applications) {
                List<AuthorityRole> authorityRoles = roleMapper.findByUserId(authorityUser.getId());
                if (authorityRoles.size() > 0) {
                    authorityUser.setRoleList(authorityRoles);
                }
            }
        }
        dataMap = applications.stream().collect(Collectors.toMap(AuthorityUser::getLoginName,
                application -> application));
    }

    public static Map<String, AuthorityUser> getDataMap() {
        return dataMap;
    }

    /**
     * 根据key值查询对应的对象
     *
     * @param loginName
     * @return
     */
    public static AuthorityUser getDataMap(String loginName) {
        return dataMap.get(loginName);
    }

    /**
     * 新增或者修改map
     *
     * @param authorityUser
     */
    public static void operatingMapList(List<AuthorityUser> authorityUser) {
        authorityUser.forEach(authorityUsers -> {
            operatingMap(authorityUsers.getLoginName());
            dataMap.put(authorityUsers.getLoginName(), authorityUsers);
        });
    }

    /**
     * 新增或者修改map
     *
     * @param authorityUser
     */
    public static void operatingMapList(AuthorityUser authorityUser) {
        operatingMap(authorityUser.getLoginName());
        dataMap.put(authorityUser.getLoginName(), authorityUser);
    }

    /**
     * 删除map
     *
     * @param loginName
     */
    public static void operatingMap(String loginName) {
        dataMap.remove(loginName);
    }
}
