package com.chegg.repository;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.chegg.entity.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
	
	@Query("SELECT p FROM Product p WHERE p.price > :price")
    List<Product> findProductsByPriceGreaterThan(@Param("price") Double price);

	@Query("SELECT p FROM Product p WHERE p.price BETWEEN :lessThen AND :greaterThen")
    List<Product> findProductsByPriceGreaterThenAndLessThen(@Param("greaterThen") Double greaterThen,@Param("lessThen") Double lessThen);
	
	
}
