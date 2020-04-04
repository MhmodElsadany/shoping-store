package com.example.ecommerce.cofig;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.Type;

import org.hibernate.boot.Metadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;

import com.example.ecommerce.entity.Product;
import com.example.ecommerce.entity.ProductCategory;




@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {
    @Autowired
	private EntityManager mngr;
	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		
		
		
	config.exposeIdsFor(mngr.getMetamodel().getEntities().
			stream().map(Type::getJavaType).toArray(Class[]::new));

	
	}
	

}
