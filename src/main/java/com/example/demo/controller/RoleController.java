package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.IRoleService;
import com.example.demo.service.IUserService;

@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {

	@Autowired
	private IUserService iUserService;
	
	@Autowired
	private IRoleService iRoleService;
	
	@PostMapping
	public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm form){
		try {
			iUserService.addRoleToUser(form.username, form.rolename);
			return ResponseEntity.ok().build();
		}catch (Exception e) {
			return ResponseEntity.ok().body(e.getMessage());
		}
	} 
	
	private class RoleToUserForm{
		String username;
		String rolename;
	}
}


