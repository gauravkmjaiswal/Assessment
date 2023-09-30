package com.chegg.controller;

import java.awt.image.BufferedImage;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.chegg.entity.Product;
import com.chegg.service.ProductService;

@org.springframework.web.bind.annotation.RestController

public class RestController {
	
	@Autowired
	public ProductService productService;
	
	@GetMapping("/")
	public String welcome()
	{
		return "ok";
	}
	
	@GetMapping("/create/product")
	public void generateProduct(@RequestParam("id") String id ,@RequestParam("name") String name ,@RequestParam("price") String price, @RequestParam("quantity") String quantity)
	{
		productService.createProduct(Long.parseLong(id), name, Double.parseDouble(price) ,Integer.parseInt(quantity));
	}
	
	 @GetMapping("findByValue") 
	 public ResponseEntity<List<Product>> findProductValueGreaterThanValue(@RequestParam("value") String value) 
	 {
		 productService.validateValue(Double.parseDouble(value));
		 List<Product> productList = productService.findByPrice(Double.parseDouble(value));
		 
		 if (!productList.isEmpty()) {
	            return ResponseEntity.ok(productList); 
	        } else {
	            return ResponseEntity.noContent().build(); 
	        }
		 
	 }
	 
	 @GetMapping("findProductBetween") 
	 public ResponseEntity<List<Product>> findProductsByPriceGreaterThanAndLessThanValue(@RequestParam("greaterThan") String greaterThan,@RequestParam("lessThan") String lessThan) 
	 {
		 productService.validateValue(Double.parseDouble(lessThan));
		 productService.validateValue(Double.parseDouble(greaterThan));
		 productService.validateValue(Double.parseDouble(greaterThan),Double.parseDouble(lessThan));
		 List<Product> productList = productService.findProductBetween(Double.parseDouble(greaterThan),Double.parseDouble(lessThan));
		 
		 if (!productList.isEmpty()) {
	            return ResponseEntity.ok(productList); // Return 200 OK with the list of products
	        } else {
	            return ResponseEntity.noContent().build(); // Return 204 No Content if the list is empty
	        }
		 
	 }
	 
}
