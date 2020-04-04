package com.example.ecommerce.entity;


import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;
import java.util.Set;

@Entity
@Table(name="product_category")
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "category_name")
    private String categoryName;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "category", cascade = CascadeType.ALL)
	@JsonIgnore
    private List<Product> products;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public ProductCategory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductCategory(Long id, String categoryName, List<Product> products) {
		super();
		this.id = id;
		this.categoryName = categoryName;
		this.products = products;
	}

	


	public ProductCategory(Long id, String categoryName) {
		super();
		this.id = id;
		this.categoryName = categoryName;
	}

}







