package com.example.ecommerce;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.ecommerce.config.SecurityUtility;
import com.example.ecommerce.config.service.UserService;
import com.example.ecommerce.entity.Role;
import com.example.ecommerce.entity.Userr;
import com.example.ecommerce.entity.UserRole;

@SpringBootApplication
public class EcommecreApplication implements CommandLineRunner {
	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(EcommecreApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		Userr user1 = new Userr();
		user1.setFirstName("mahmoud");
		user1.setLastName("ayman");
		user1.setUsername("mm");
		user1.setPassword(SecurityUtility.passwordEncoder().encode("mm"));
		user1.setEmail("mahmoud@gmail.com");
		Set<UserRole> userRoles = new HashSet<>();
		Role role1 = new Role();
		role1.setRoleId(1);
		role1.setName("ROLE_USER");
		userRoles.add(new UserRole(user1, role1));
		
		userService.createUser(user1, userRoles);
		
		userRoles.clear();
		
		Userr user2 = new Userr();
		user2.setFirstName("Admin");
		user2.setLastName("Admin");
		user2.setUsername("admin");
		user2.setPassword(SecurityUtility.passwordEncoder().encode("p"));
		user2.setEmail("Admin@gmail.com");
		Role role2 = new Role();
		role2.setRoleId(0);
		role2.setName("ROLE_ADMIN");
		userRoles.add(new UserRole(user2, role2));
		
		userService.createUser(user2, userRoles);
	}
}
