package com.yugabyte.ysql.sample.r2dbcysqlclient.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.yugabyte.ysql.sample.r2dbcysqlclient.domain.Product;

import reactor.core.publisher.Flux;

public interface ProductRepository extends ReactiveCrudRepository<Product, Integer> {

  // Example of using custom query
	@Query("SELECT * FROM products WHERE LOWER(product_name) like LOWER(:productName)")
	Flux<Product> findProducts(String productName);

  // Example of using Spring Data native query generator
  Flux<Product> findByProductNameContainingIgnoreCase(String productName);

  Flux<Product> findByPriceIsGreaterThan(double amount);

}
