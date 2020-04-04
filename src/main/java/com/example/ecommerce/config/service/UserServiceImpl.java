package com.example.ecommerce.config.service;

import java.util.Set;

import javax.transaction.Transactional;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerce.dao.CartUser;
import com.example.ecommerce.dao.RoleRepository;
import com.example.ecommerce.entity.Userr;
import com.example.ecommerce.entity.UserRole;

import ch.qos.logback.classic.Logger;

@Service
public class UserServiceImpl implements UserService{
	
	
	@Autowired
	private CartUser userRepository;
	
	@Autowired
	private RoleRepository roleRepository;

	public Userr createUser(Userr user, Set<UserRole> userRoles) {
		Userr localUser = userRepository.findByUsername(user.getUsername());
		
		if(localUser != null) {
			//LOG.info("User with username {} already exist. Nothing will be done. ", user.getUsername());
		} else {
			for (UserRole ur : userRoles) {
				roleRepository.save(ur.getRole());
			}
			
			user.getUserRoles().addAll(userRoles);
			
			localUser = userRepository.save(user);
		}
		
		return localUser;
	}


	@Override
	public Userr findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	@Override
	public Userr findByEmail(String email) {
		return userRepository.findByEmail(email);
	}


	@Override
	public Userr save(Userr user)  {
		return userRepository.save(user);
	}
	
	

}
