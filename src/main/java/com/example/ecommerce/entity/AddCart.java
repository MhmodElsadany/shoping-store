package com.example.ecommerce.entity;



import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name = "addcart")

public class AddCart {
	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long id;
	    private String username;
	    
	    @OneToOne
	    private Userr user;
	    
	    public Userr getUser() {
			return user;
		}
		public void setUser(Userr user) {
			this.user = user;
		}

		@OneToMany
	    private List <Product> products;
	    
		public AddCart(Long id, String username, List<Product> products) {
			super();
			this.id = id;
			this.username = username;
			this.products = products;
		}
		public List<Product> getProducts() {
			return products;
		}
		public void setProducts(List<Product> products) {
			this.products = products;
		}
		
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		
		public AddCart() {
			super();
			// TODO Auto-generated constructor stub
		}
	    
	    
	
}
