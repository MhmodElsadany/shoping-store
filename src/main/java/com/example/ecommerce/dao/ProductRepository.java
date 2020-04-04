package com.example.ecommerce.dao;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.ecommerce.entity.AddCart;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.viewmodel.ProductViewModel;

@CrossOrigin("http://localhost:4200")
@RepositoryRestResource(collectionResourceRel = "products", path = "product")
public interface ProductRepository extends JpaRepository<Product, Long> {
	@CrossOrigin("http://localhost:4200")
	@RestResource(path = "categoryid")
	List<Product> findByCategoryId(@Param("id") Long id );

	@RestResource(path = "cartid")
	List<Product> findAllByUserId(@Param("id") Long id );
	
	    Product findByName(String name );
	    @Modifying
	    @Query("delete from Product b where b.name=:name")
	    void deletingByName(String name );


	
}
