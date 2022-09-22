package com.example.demo;

import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.service.IRoleService;
import com.example.demo.service.IUserService;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	CommandLineRunner run(IUserService userService, IRoleService roleService) {
		return args -> {
			roleService.save(new Role("ROLE_ADMIN"));
			roleService.save(new Role("ROLE_USER"));
			roleService.save(new Role("ROLE_MANAGER"));
			roleService.save(new Role("ROLE_SUPERADMIN"));
			
			userService.saveUser(new User("sadhsaudksahdjhaskj","John", "JohnDoe", "1234", new ArrayList<>()));
			userService.saveUser(new User("Dang", "DangKen", "1234", new ArrayList<>()));
			userService.saveUser(new User("Lily", "MarketLil", "1234", new ArrayList<>()));
			userService.saveUser(new User("Yeppik", "Kik132", "1234", new ArrayList<>()));
		
			userService.addRoleToUser("JohnDoe", "ROLE_ADMIN");
			userService.addRoleToUser("DangKen", "ROLE_MANAGER");
			userService.addRoleToUser("MarketLil", "ROLE_USER");
			userService.addRoleToUser("Kik132", "ROLE_SUPERADMIN");
		};
	}

}
