package com.aligntechnology.sampletask.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.aligntechnology.sampletask.model.Product;
import com.aligntechnology.sampletask.service.ProductService;
import com.aligntechnology.sampletask.util.CustomErrorType;

@RestController
@RequestMapping("/api")
public class RestApiController {

	public static final Logger logger = LoggerFactory.getLogger(RestApiController.class);

	@Autowired
	ProductService productService; //Service which will do all data retrieval/manipulation work

	// -------------------Retrieve Single Product------------------------------------------

	@RequestMapping(value = "/product/{name}/{brand}", method = RequestMethod.GET)
	public ResponseEntity<?> getProductByNameAndBrand(@PathVariable("name") String name,
											  @PathVariable("brand") String brand) {

		logger.info("Fetching Product with name {} and brand {}", name, brand);
		Product product = productService.findByNameAndBrand(name, brand);
		if (product == null) {
			logger.error("Product with name {} not found.", name);
			return new ResponseEntity(new CustomErrorType("Product with name " + name
					+ " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}

	// -------------------Create a Product-------------------------------------------

	@RequestMapping(value = "/product/", method = RequestMethod.POST)
	public ResponseEntity<?> createProduct(@RequestBody Product product, UriComponentsBuilder ucBuilder) {
		logger.info("Creating Product : {}", product);

		if (productService.isProductExist(product)) {
			logger.error("Unable to create. A Porduct with name {} already exist", product.getName());
			return new ResponseEntity(new CustomErrorType("Unable to create. A Product with name " +
			product.getName() + " already exist."),HttpStatus.CONFLICT);
		}
		productService.saveProduct(product);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/product/{name}/{brand}").buildAndExpand(product.getName(), product.getBrand()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	// ------------------- Update a Product ------------------------------------------------

	@RequestMapping(value = "/product/{name}/{brand}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateProduct(@PathVariable("name") String name,
										   @PathVariable("brand") String brand,
										   @RequestBody Product product) {

		logger.info("Updating Product with name {}", name);

		Product currentProduct = productService.findByNameAndBrand(name, brand);

		if (currentProduct == null) {
			logger.error("Unable to update. Product with name {} not found.", name);
			return new ResponseEntity(new CustomErrorType("Unable to upate. Product with name " + name + " not found."),
					HttpStatus.NOT_FOUND);
		}

		currentProduct.setName(product.getName());
		currentProduct.setBrand(product.getBrand());
		currentProduct.setPrice(product.getPrice());
		currentProduct.setQuantity(product.getQuantity());

		productService.updateProduct(currentProduct);
		return new ResponseEntity<Product>(currentProduct, HttpStatus.OK);
	}

	// ------------------- Delete a Product-----------------------------------------

	@RequestMapping(value = "/product/{name}/{brand}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteProduct(@PathVariable("name") String name,
										   @PathVariable("brand") String brand) {

		logger.info("Fetching & Deleting Product with name {} and brand {}", name, brand);

		Product product = productService.findByNameAndBrand(name, brand);
		if (product == null) {
			logger.error("Unable to delete. Product with name {} not found.", name);
			return new ResponseEntity(new CustomErrorType("Unable to delete. Product with name " + name + " not found."),
					HttpStatus.NOT_FOUND);
		}
		productService.deleteProductByNameAndBrand(name, brand);
		return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
	}

	// ------------------- Get All Leftovers-----------------------------

	@RequestMapping(value = "/product/leftovers", method = RequestMethod.GET)
	public ResponseEntity<List<Product>> getLeftovers() {
		logger.info("Getting leftovers");

		List<Product> products = productService.findAllLeftovers();
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}

}