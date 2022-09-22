package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.service.IUserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

	@Autowired
	private IUserService iUserService;
	
	@GetMapping
	public ResponseEntity<List<User>> getUsers(){
		return ResponseEntity.ok().body(iUserService.findAll());
	}
	
	@PostMapping
	public ResponseEntity<User> saveUser(@RequestBody User user){
		return  ResponseEntity.ok().body(iUserService.saveUser(user));
	}
	
}
