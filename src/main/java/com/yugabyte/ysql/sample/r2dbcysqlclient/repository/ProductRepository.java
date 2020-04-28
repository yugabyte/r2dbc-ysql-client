package com.yugabyte.ysql.sample.r2dbcysqlclient.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.yugabyte.ysql.sample.r2dbcysqlclient.domain.Product;

import reactor.core.publisher.Flux;

public interface ProductRepository extends ReactiveCrudRepository<Product, Integer> {
	
	@Query("SELECT * FROM customer WHERE last_name = :productName")
	Flux<Product> findByProductName(String productName);

}
