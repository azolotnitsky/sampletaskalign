package com.aligntechnology.sampletask.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.aligntechnology.sampletask.model.Product;

@Service("productService")
public class ProductServiceImpl implements ProductService {
	
	private static List<Product> products;
	
	static{
		products = populateDummyProducts();
	}

	public Product findByNameAndBrand(String name, String brand) {
		for(Product product : products){
			if (product.getName().equalsIgnoreCase(name) &&
				product.getBrand().equalsIgnoreCase(brand)){

					return product;
			}
		}
		return null;
	}
	
	public void saveProduct(Product product) {
		products.add(product);
	}

	public void updateProduct(Product product) {
		int index = products.indexOf(product);
		products.set(index, product);
	}

	public void deleteProductByNameAndBrand(String name, String brand) {
		
		for (Iterator<Product> iterator = products.iterator(); iterator.hasNext(); ) {
		    Product product = iterator.next();
		    if (product.getName().equals(name) && product.getBrand().equals(brand)) {
		        iterator.remove();
		    }
		}
	}

	public List<Product> findAllLeftovers() {

		List<Product> resultList = new ArrayList<Product>();
		for (Iterator<Product> iterator = products.iterator(); iterator.hasNext(); ) {
			Product product = iterator.next();
			if (product.getQuantity() < 5) {
				resultList.add(product);
			}
		}

		return resultList;

	}

	public boolean isProductExist(Product product) {
		return findByNameAndBrand(product.getName(), product.getBrand()) != null;
	}

	private static List<Product> populateDummyProducts(){
		List<Product> products = new ArrayList<Product>();
		products.add(new Product("washingmachine", "electrolux", 40.5, 10));
		products.add(new Product("microoven", "indesit", 60.5, 12));
		products.add(new Product("refrigerator", "whirlpool", 70.5, 4));
		products.add(new Product("multivarka", "redmond", 10.5, 3));
		return products;
	}



}
