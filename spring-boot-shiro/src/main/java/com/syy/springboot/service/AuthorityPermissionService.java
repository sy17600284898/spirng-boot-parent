package com.syy.springboot.service;



import com.syy.springboot.model.AuthorityPermission;
import com.syy.springboot.result.BaseResponse;

import java.util.List;


/**
 * @author ASUS
 */
public interface AuthorityPermissionService {

    /**
     * findAll
     *
     * @return
     */
    List<AuthorityPermission> getPermissionList();

    /**
     * saveAndUpdate
     *
     * @param authorityPermission
     * @return
     */
    BaseResponse<Object> operatingAuthorityPermission(AuthorityPermission authorityPermission);

    /**
     * findByOne
     *
     * @param id
     * @return
     */
    AuthorityPermission queryByPermissionId(int id);

    /**
     * delete
     *
     * @param id
     * @return
     */
    BaseResponse<Object> deletePermissionById(int id);
}
