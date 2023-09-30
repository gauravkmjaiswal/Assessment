package com.chegg.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.chegg.entity.Product;
import com.chegg.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	public List<Product> findByPrice(Double price)
	{
		return productRepository.findProductsByPriceGreaterThan(price);
	}
	public List<Product> findProductBetween(Double priceLessThen, Double priceGreaterThen)
	{
		return productRepository.findProductsByPriceGreaterThenAndLessThen(priceGreaterThen,priceLessThen);
	}
	
	public void createProduct(long id, String name, double price, int quantity)
	{
		Product product = new Product(id, name, price, quantity);
		productRepository.save(product);
	}
	
	public void validateValue(double value)
	{
		if(value<0)
		{
			throw new IllegalArgumentException("value can't be negative");
		}
	}
	public void validateValue(double lessThan, double greaterThan)
	{
		if (lessThan>= greaterThan) {
            throw new IllegalArgumentException("greaterThan must be less than lessThan");
        }
	}
}
