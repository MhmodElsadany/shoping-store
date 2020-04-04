package com.example.ecommerce.map;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.ecommerce.dao.ProductCategoryRepository;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.entity.ProductCategory;
import com.example.ecommerce.entity.Userr;
import com.example.ecommerce.viewmodel.CartViewModelProduct;
import com.example.ecommerce.viewmodel.ProductViewModel;

public class Mapper {
	
	@Autowired
    private ProductCategoryRepository notebookRepository;


    public Mapper(ProductCategoryRepository notebookRepository) {
		super();
		this.notebookRepository = notebookRepository;
	}
	
	public ProductViewModel convertToNoteViewModel(Product entity) {
		
		
		ProductViewModel viewModel = new ProductViewModel();
        viewModel.setActive(entity.isActive());
        viewModel.setDescription(entity.getDescription());
        viewModel.setDateCreated(entity.getDateCreated());
        viewModel.setId(entity.getId());
        viewModel.setImageUrl(entity.getImageUrl());
        viewModel.setName(entity.getName());
        viewModel.setLastUpdated(entity.getLastUpdated());
        viewModel.setSku(entity.getSku());
        viewModel.setUnitPrice(entity.getUnitPrice());
        viewModel.setUnitsInStock(entity.getUnitsInStock());
        viewModel.setCategory(entity.getCategory().getId()+"");

        return viewModel;
    }
	
	 public Product convertToNoteEntity(ProductViewModel viewModel ,ProductCategory notebook ) {
         System.out.print("ttttttttttttttttttttttt");

           System.out.print(viewModel.getId());
           System.out.print("ttttttttttttttttttttttt");
         System.out.print("tttttttttttttttttttttttpppp");
         System.out.print(notebook.getCategoryName());
         System.out.print("tttttttttttttttttttttttppppp");


	    	Product entity = new Product(viewModel.getId(), notebook, viewModel.getSku(), viewModel.getName(), viewModel.getDescription(), viewModel.getUnitPrice(),
	    			viewModel.getImageUrl(), viewModel.isActive(), viewModel.getUnitsInStock(), viewModel.getDateCreated(), viewModel.getLastUpdated(),null) ;
	        return entity;
	    }

	 
	 //carrt
	 public Product convertToNoteEntityCart(CartViewModelProduct viewModel ,Userr user , ProductCategory notebook) {
         System.out.print("ttttttttttttttttttttttt");

           System.out.print(viewModel.getId());
           System. out.print("ttttttttttttttttttttttt");
         System.out.print("tttttttttttttttttttttttpppp");


	    	Product entity = new Product(viewModel.getId(), notebook, viewModel.getSku(), viewModel.getName(), viewModel.getDescription(), viewModel.getUnitPrice(),
	    			viewModel.getImageUrl(), viewModel.isActive(), viewModel.getUnitsInStock(), viewModel.getDateCreated(), viewModel.getLastUpdated(),user) ;
	        return entity;
	    }

	public CartViewModelProduct convertToNoteViewModelCart(Product entity) {

		CartViewModelProduct viewModel = new CartViewModelProduct();
		viewModel.setActive(entity.isActive());
		viewModel.setDescription(entity.getDescription());
		viewModel.setDateCreated(entity.getDateCreated());
		viewModel.setId(entity.getId());
		viewModel.setImageUrl(entity.getImageUrl());
		viewModel.setName(entity.getName());
		viewModel.setLastUpdated(entity.getLastUpdated());
		viewModel.setSku(entity.getSku());
		viewModel.setUnitPrice(entity.getUnitPrice());
		viewModel.setUnitsInStock(entity.getUnitsInStock());
		viewModel.setCategory(entity.getCategory().getId() + "");

		try {
			viewModel.setUserId(entity.getAddcart().getId() + "");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return viewModel;
	}

}
