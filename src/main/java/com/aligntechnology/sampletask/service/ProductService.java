package com.aligntechnology.sampletask.service;


import java.util.List;

import com.aligntechnology.sampletask.model.Product;

public interface ProductService {
	
	Product findByNameAndBrand(String name, String brand);

	void saveProduct(Product product);
	
	void updateProduct(Product product);
	
	void deleteProductByNameAndBrand(String name, String brand);

	List<Product> findAllLeftovers();

	boolean isProductExist(Product product);

}
