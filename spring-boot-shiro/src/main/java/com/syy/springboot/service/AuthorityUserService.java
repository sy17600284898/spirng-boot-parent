package com.syy.springboot.service;


import com.syy.springboot.model.AuthorityUser;
import com.syy.springboot.model.AuthorityUserRole;
import com.syy.springboot.result.BasePageNewResponse;
import com.syy.springboot.util.Page;

import java.util.List;


/**
 * @author ASUS
 */
public interface AuthorityUserService {

    /**
     * saveAndUpdate
     *
     * @param list
     * @param uid
     * @return
     */
    boolean operatingAuthorityUserRole(List<AuthorityUserRole> list, int uid);

    /**
     * findByOne
     *
     * @param uid
     * @return
     */
    AuthorityUser queryByUid(int uid);

    /**
     * saveAndUpdates
     *
     * @param ui
     * @return
     */
    boolean operatingAuthorityUser(AuthorityUser ui);

    /**
     * getUserList
     *
     * @param toEntity
     * @param toPage
     * @return
     */
    BasePageNewResponse getUserList(AuthorityUser toEntity, Page toPage);

    /**
     * getDataAll
     *
     * @return
     */
    List<AuthorityUser> getDataAll();
}
