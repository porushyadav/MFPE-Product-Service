package com.simulation.project.services;


import java.lang.module.FindException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.simulation.project.DAO.ProductDAO;
import com.simulation.project.exception.AlreadyExistException;
import com.simulation.project.exception.ErrorResponse;
import com.simulation.project.model.Product;

@Service
public class ProductService {

	@Autowired
	ProductDAO dao;


	public Optional<Product> getProductId(int id) {
		return dao.findById(id);
	}
	

	public Optional<Product> getProductName(String name) {
		return dao.findByName(name);
	}
	
	

	public ErrorResponse addRatings(Product product,int id) {
		
		if(!dao.findById(id).isPresent())
			{
				throw new NullPointerException("Product not found!");
			}
		Product p=dao.findById(id)
				.orElseThrow();
		
		float avg_rating=p.getRating();
		float curr_user_rating=product.getRating();
		int count=p.getCount();
		
		float avg=(avg_rating*count+curr_user_rating)/(count+1);
		p.setRating(avg);
		p.setCount(count+1);
		dao.save(p);
		return new ErrorResponse(HttpStatus.OK, "Updated Successfully");
	}
	
	public List<Product> getProducts()
	{
		return (List<Product>) dao.findAll();
	}
	

	public ErrorResponse addProduct(Product product)
	{
		if(dao.findById(product.getId()).isPresent())
		{
			throw new AlreadyExistException("Product already exist!");
		}
		 dao.save(product);
		 return new ErrorResponse(HttpStatus.OK, "Product added successfully");
	}
	
	

	
}
