package com.example.demo.service.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.IRoleRepository;
import com.example.demo.repository.IUserRepository;
import com.example.demo.service.IUserService;

@Service
@Transactional
public class UserServiceImpl implements IUserService, UserDetailsService {

	@Autowired
	private IUserRepository iUserRepository;

	@Autowired
	private IRoleRepository iRoleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public List<User> findAll() {
		try {
			return iUserRepository.findAll();
		} catch (Exception e) {
			throw new RuntimeException("Query List User Failed");
		}
	}

	@Override
	public User saveUser(User user) {
		try {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			return iUserRepository.save(user);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public User findByUsername(String userName) {
		try {
			return iUserRepository.findByUsername(userName);
		} catch (Exception e) {
			throw new RuntimeException("Cannot found " + userName);
		}
	}

	@Override
	public void addRoleToUser(String username, String name) {
		try {
			User user = iUserRepository.findByUsername(username);
			Role role = iRoleRepository.findByName(name);
			if (user != null) {
				if (role != null) {
					user.getRoles().add(role);
					iUserRepository.save(user);
				} else {
					throw new RuntimeException("Cannot found  role" + role);
				}
			} else {
				throw new RuntimeException("Cannot found  username" + username);
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = iUserRepository.findByUsername(username);
		if(user == null) {
			throw new UsernameNotFoundException("User not found in database");
		}
		Collection<SimpleGrantedAuthority> authorities = new HashSet<>();
		user.getRoles().forEach(role -> {authorities.add(new SimpleGrantedAuthority(role.getName()));});
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
	}

}
