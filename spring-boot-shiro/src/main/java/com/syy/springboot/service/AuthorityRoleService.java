package com.syy.springboot.service;


import com.syy.springboot.model.AuthorityRole;
import com.syy.springboot.model.AuthorityRolePermission;
import com.syy.springboot.model.AuthorityUserRole;
import com.syy.springboot.result.BasePageNewResponse;
import com.syy.springboot.util.Page;

import java.util.List;

/**
 * @author ASUS
 */
public interface AuthorityRoleService {

    /**
     * operatingAuthorityRole
     *
     * @param authorityRole
     * @return
     */
    boolean operatingAuthorityRole(AuthorityRole authorityRole);

    /**
     * queryByRoleId
     *
     * @param id
     * @return
     */
    AuthorityRole queryByRoleId(int id);

    /**
     * saveAndUpdateRolePermission
     *
     * @param list
     * @param id
     */
    void operatingRolePermission(List<AuthorityRolePermission> list, int id);

    /**
     * getRoleList
     *
     * @param toEntity
     * @param toPage
     * @return
     */
    BasePageNewResponse getRoleList(AuthorityRole toEntity, Page toPage);

    /**
     * queryByUserId
     *
     * @param roleId
     * @return
     */
    List<AuthorityUserRole> queryByUserId(int roleId);
}
