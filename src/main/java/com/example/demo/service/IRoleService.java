package com.example.demo.service;

import com.example.demo.entity.Role;

public interface IRoleService {
	Role save(Role role);
	Role findByName(String name);
}
