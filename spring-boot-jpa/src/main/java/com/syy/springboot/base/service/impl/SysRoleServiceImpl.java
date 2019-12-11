package com.syy.springboot.base.service.impl;

import com.syy.springboot.base.dao.SysRoleDao;
import com.syy.springboot.base.entity.SysRole;
import com.syy.springboot.base.entity.UserInfo;
import com.syy.springboot.base.service.SysRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class SysRoleServiceImpl implements SysRoleService {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());  
    @Autowired
    private SysRoleDao sysRoleDao;
	@Override
	public UserInfo findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Page<SysRole> findByRole(String role, Pageable pageable) {
		// TODO Auto-generated method stub
		return sysRoleDao.findByRole(role, pageable);
	}
	@Override
	public Page<SysRole> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return sysRoleDao.findAll(pageable);
	}
	@Override
	@Transactional
	public int updateState(boolean available, String uid) {
		// TODO Auto-generated method stub
		System.out.print("hhhe--------------"+uid);
		int id = Integer.parseInt(uid);
		SysRole sr = sysRoleDao.findById(id);
		sysRoleDao.delete(sr);
		return 1;
	}
	@Override
	public SysRole save(SysRole sr) {
		
		return sysRoleDao.save(sr);
	}
	@Override
	public int updateRole(SysRole sr) {
		// TODO Auto-generated method stub
		String description = sr.getDescription();
		String role = sr.getRole();
		String id = sr.getId()+"";
		return sysRoleDao.updateRole(description, role, id);
	}
	@Override
	public SysRole findByOne(String id) {
		int uid = Integer.parseInt(id);
		return sysRoleDao.findById(uid);
	}
	@Override
	public List<SysRole> findAll() {
		// TODO Auto-generated method stub
		return (List<SysRole>) sysRoleDao.findAll();
	}
}