package com.syy.springboot.base.service;

import com.syy.springboot.base.entity.SysPermission;
import com.syy.springboot.base.entity.SysPermissionVo;

import java.util.List;

public interface SysPermissionService {
	
	
	 SysPermission findById(int id);
	    
	 List<SysPermissionVo> findAll();
	
	
	SysPermission save(SysPermission sp);
	
	int updatePermission(SysPermission sp); 
	
	
	SysPermission findByOne(String id);
	
	int delete(String id);
}