package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.User;

public interface IUserService {
	List<User> findAll();
	/*User findById(Long id);*/
	User saveUser(User user);
	User findByUsername(String userName);
	void addRoleToUser(String username, String name);
}
