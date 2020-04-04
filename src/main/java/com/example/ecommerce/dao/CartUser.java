package com.example.ecommerce.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.example.ecommerce.entity.AddCart;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.entity.ProductCategory;
import com.example.ecommerce.entity.Userr;

@CrossOrigin("http://localhost:4200")

@RepositoryRestResource(collectionResourceRel = "productsCart", path = "productcart")

public interface CartUser extends JpaRepository<Userr, Long> {

	

    
    public Userr findAllByEmailAndPassword(String email , String password);  
	Userr findByUsername(String username);
	Userr findByEmail(String email);
	
	List<Userr> findAll();



}
