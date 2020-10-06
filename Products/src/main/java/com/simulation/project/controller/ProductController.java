package com.simulation.project.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simulation.project.DAO.ProductDAO;
import com.simulation.project.exception.ErrorResponse;
import com.simulation.project.model.Product;

import com.simulation.project.services.ProductService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
public class ProductController {

	@Autowired
	private ProductService productService;
		
	
	
       ///search product by id
	@GetMapping("/searchid/{id}")
	public Optional<Product> searchProductById(@PathVariable("id") int id)
	{
		log.debug("inside searchProductById() method");
		return productService.getProductId(id);
		
	}
	// search product by name
	@GetMapping("/searchname/{name}")
	public Optional<Product> searchProductById(@PathVariable("name") String name)
	{
		log.debug("inside searchProductByName() method");
		return productService.getProductName(name);
		
	}
	
    //get rating from the user
	@PutMapping("/rating/{id}")
	public ErrorResponse addProductRating(@RequestBody Product product,@PathVariable("id") int id)
	{
		log.debug("inside searchProductByName() method");
		return productService.addRatings(product,id);
		
	}
	
	
	
    //add a product to the database
	@PostMapping(value="/addproduct" )
	public ResponseEntity<ErrorResponse> createProduct(@RequestBody Product product)
	{
		log.debug("inside createProduct() method");
		return ResponseEntity.ok(productService.addProduct(product));
	}
	//get list of al the products
	@GetMapping(value="/getproducts" )
	public List<Product> getProducts()
	{
		log.debug("inside getProducts() method");
		return productService.getProducts();
	}
	
	
	

	

}
