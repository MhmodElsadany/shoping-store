package com.example.ecommerce.config.service;

import java.util.Set;

import com.example.ecommerce.entity.Userr;
import com.example.ecommerce.entity.UserRole;


public interface UserService {
	
	Userr createUser(Userr user, Set<UserRole> userRoles);
    Userr findByUsername(String username);
	
	Userr findByEmail (String email);
	Userr save(Userr user);
}