package com.syy.springboot.base.dao;

import com.syy.springboot.base.entity.SysPermission;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface SysPermissionDao extends CrudRepository<SysPermission, Long> {

    SysPermission findById(int id);


    @Modifying(clearAutomatically = true)
    @Query(value = "update sys_permission s set s.name=?1,s.resource_type=?2,s.url=?3,s.permission=?4,s.available=?5 where s.id = ?6", nativeQuery = true)
    int updatePermission(String name, String resourceType, String url, String permission, boolean available, String id);


    @Modifying(clearAutomatically = true)
    @Query(value = "delete from sys_permission  where id = ?1", nativeQuery = true)
    int delete(int id);

}