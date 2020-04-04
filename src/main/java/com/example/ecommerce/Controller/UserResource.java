package com.example.ecommerce.Controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import com.example.ecommerce.config.SecurityUtility;
import com.example.ecommerce.config.service.UserService;
import com.example.ecommerce.entity.Role;
import com.example.ecommerce.entity.UserRole;
import com.example.ecommerce.entity.Userr;
import com.example.ecommerce.utility.MailConstructor;

@RestController
@RequestMapping("/api/create")
@CrossOrigin("http://localhost:4200")

public class UserResource {
	
	@Autowired
	private UserService userService;
	@Autowired
	private MailConstructor mailConstructor;

	@Autowired
	private JavaMailSender mailSender;
	
	@RequestMapping(value = "/newUser", method = RequestMethod.POST)
	public ResponseEntity newUserPost( @RequestBody HashMap<String, String> mapper)
			throws Exception {
		String username = mapper.get("username");
		String userEmail = mapper.get("email");

		if (userService.findByUsername(username) != null) {
			return new ResponseEntity("usernameExists", HttpStatus.BAD_REQUEST);
		}

		if (userService.findByEmail(userEmail) != null) {
			return new ResponseEntity("emailExists", HttpStatus.BAD_REQUEST);
		}

		Userr user = new Userr();
		user.setUsername(username);
		user.setEmail(userEmail);

		String password = SecurityUtility.randomPassword();

		String encryptedPassword = SecurityUtility.passwordEncoder().encode(password);
		user.setPassword(encryptedPassword);

		Role role = new Role();
		role.setRoleId(1);
		role.setName("ROLE_USER");
		Set<UserRole> userRoles = new HashSet<>();
		userRoles.add(new UserRole(user, role));
		userService.createUser(user, userRoles);

		SimpleMailMessage email = mailConstructor.constructNewUserEmail(user, password);
		mailSender.send(email);

		return new ResponseEntity("User Added Successfully!", HttpStatus.OK);

	}
	
	@RequestMapping(value = "/forgetPassword", method = RequestMethod.POST)
	public ResponseEntity forgetPasswordPost( @RequestBody HashMap<String, String> mapper)
			throws Exception {

		Userr user = userService.findByEmail(mapper.get("email"));

		if (user == null) {
			return new ResponseEntity("Email not found", HttpStatus.BAD_REQUEST);
		}
		String password = SecurityUtility.randomPassword();

		String encryptedPassword = SecurityUtility.passwordEncoder().encode(password);
		user.setPassword(encryptedPassword);
		userService.save(user);

		SimpleMailMessage newEmail = mailConstructor.constructNewUserEmail(user, password);
		mailSender.send(newEmail);

		return new ResponseEntity("Email sent!", HttpStatus.OK);

	}
	
	
	
	
	

}
