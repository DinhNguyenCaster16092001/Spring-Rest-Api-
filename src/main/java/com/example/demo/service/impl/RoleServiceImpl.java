package com.example.demo.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Role;
import com.example.demo.repository.IRoleRepository;
import com.example.demo.service.IRoleService;

@Service
@Transactional
public class RoleServiceImpl implements IRoleService{

	@Autowired
	private IRoleRepository iRoleRepository;
	
	@Override
	public Role save(Role role) {
		try{
			return iRoleRepository.save(role);
		}catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public Role findByName(String name) {
		try{
			return iRoleRepository.findByName(name);
		}catch (Exception e) {
			throw new RuntimeException("Cannot found role " + name);
		}
	}

}
