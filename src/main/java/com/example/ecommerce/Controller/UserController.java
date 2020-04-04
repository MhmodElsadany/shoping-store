package com.example.ecommerce.Controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.dao.CartUser;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.entity.ProductCategory;
import com.example.ecommerce.entity.Userr;
import com.example.ecommerce.viewmodel.ProductViewModel;
import com.example.ecommerce.viewmodel.UserVM;

@RestController
@RequestMapping("/api/user")
@CrossOrigin("http://localhost:4200")

public class UserController {
	
	
	@Autowired
	CartUser cartUSerRepository;
	
	
	@PostMapping("/getuser")
	public Userr getUSer(@RequestBody UserVM user , BindingResult bindingResult) {
    	Userr userData   = this.cartUSerRepository.findAllByEmailAndPassword(user.getEmail(), user.getPassword());
    	return userData;

	}
	
	
	@PostMapping("/saveuser")
    public Userr save(@RequestBody Userr user,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
        }
        Userr dataUser=user;


        this.cartUSerRepository.save(dataUser);


        return user;
    }
	
	
	@RequestMapping("/token")
	public Map<String, String> token(HttpSession session, HttpServletRequest request) {
		System.out.println(request.getRemoteHost());
		
		String remoteHost = request.getRemoteHost();
		int portNumber = request.getRemotePort();
		
		System.out.println(remoteHost+":"+portNumber);
		System.out.println(request.getRemoteAddr());
		
		return  Collections.singletonMap("token", session.getId());
	}
	@RequestMapping("/checkSession")
	public ResponseEntity checkSession() {
		return new ResponseEntity("Session Active!", HttpStatus.OK);
	}
	@RequestMapping(value="/logout", method=RequestMethod.POST)
	public ResponseEntity logout(){
		SecurityContextHolder.clearContext();
		return new ResponseEntity("Logout Successfully!", HttpStatus.OK);
	}
	
	
	
	
	
}
