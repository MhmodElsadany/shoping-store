package com.example.ecommerce.Controller;


import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.xml.bind.ValidationException;

import org.hibernate.MappingNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.ecommerce.dao.CartUser;
import com.example.ecommerce.dao.ProductCategoryRepository;
import com.example.ecommerce.dao.ProductRepository;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.entity.ProductCategory;
import com.example.ecommerce.entity.Userr;
import com.example.ecommerce.map.Mapper;
import com.example.ecommerce.viewmodel.CartViewModelProduct;
import com.example.ecommerce.viewmodel.ProductViewModel;

@RestController
@RequestMapping("/api/product")
@CrossOrigin("http://localhost:4200")
public class ProductController {
	@Autowired
	ProductRepository pro;
	@Autowired
	ProductCategoryRepository productCategoryRepo;
	@Autowired
	CartUser cartUSerRepository;

	
	Mapper map =new Mapper(productCategoryRepo);

	@GetMapping("/all")
	public List <ProductViewModel> getData(){
    	List<Product> notes = this.pro.findAll();

        // map from entity to view model
    	List<ProductViewModel> prmv = notes.stream()
                .map(note -> this.map.convertToNoteViewModel(note))
                .collect(Collectors.toList());

        return prmv;	}
	
	@PostMapping("/save")
    public Product save(@RequestBody ProductViewModel productViewModel,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
        }

       ProductCategory pr = this.productCategoryRepo.findById(Long.parseLong(1+"")).orElse(null);
        Product notebookEntity = this.map.convertToNoteEntity(productViewModel , pr);

        // save notebookEntity instance to db
        this.pro.save(notebookEntity);

        return notebookEntity;
    }
	
	
	

	
	@DeleteMapping ("/delet/{id}")
    public void delet(@PathVariable long id) {
        
        // save notebookEntity instance to db
        this.pro.deleteById(id);

    }
	
	
	
	//get and add cart
	@PostMapping("/addcart")
    public CartViewModelProduct updatingCart(@RequestBody CartViewModelProduct productViewModel,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
        }

       ProductCategory pr = this.productCategoryRepo.findById(Long.parseLong(1+"")).orElse(null);
       Userr user = this.cartUSerRepository.findById(Long.parseLong(1+"")).orElse(null);

        Product notebookEntity = this.map.convertToNoteEntityCart(productViewModel, user, pr);

        // save notebookEntity instance to db
        this.pro.save(notebookEntity);

        return productViewModel;
    }
	
	@GetMapping("/all-cart")
	public List <CartViewModelProduct> getDataCart(){
    	List<Product> notes = this.pro.findAll();

        // map from entity to view model
    	List<CartViewModelProduct> prmv = notes.stream()
                .map(note -> this.map.convertToNoteViewModelCart(note))
                .collect(Collectors.toList());

        return prmv;	}
	
	@GetMapping("/getprodect/{username}")
	public ProductViewModel getPro(@PathVariable String username){
		
		 
			  Product prodect = this.pro.findByName(username);
		        if (prodect==null) {
		        	throw new ResponseStatusException(
		 		           HttpStatus.NOT_FOUND, "Foo Not Found");
		        }else{
		        // map from entity to view model
		    	ProductViewModel prmv = this.map.convertToNoteViewModel(prodect);
		        return prmv;		
		        }
        }
	@Transactional
	@DeleteMapping("/deletprodect/{username}")
	public String deletpro(@PathVariable String username){
    	this.pro.deletingByName(username);
    	return "success";
        }
	
	@PutMapping("/updatepro")
    public Product updateProduct(@RequestBody ProductViewModel productViewModel,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
        }
        



		  Product prodect = this.pro.findByName(productViewModel.getName());
        // save notebookEntity instance to db
		  
	        if (prodect==null) {
	        	
	        	
	        	throw new ResponseStatusException(
	 		           HttpStatus.NOT_FOUND, "Foo Not Found");
	        }else{
	            ProductCategory pr = this.productCategoryRepo.findById(Long.parseLong(productViewModel.getCategory_id()+"")).orElse(null);
	            Product notebookEntity = this.map.convertToNoteEntity(productViewModel , pr);


        this.pro.save(notebookEntity);
        return prodect;
	        }
        
    }	
	
}