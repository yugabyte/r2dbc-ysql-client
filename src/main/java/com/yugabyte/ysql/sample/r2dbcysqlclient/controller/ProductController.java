package com.yugabyte.ysql.sample.r2dbcysqlclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yugabyte.ysql.sample.r2dbcysqlclient.domain.Product;
import com.yugabyte.ysql.sample.r2dbcysqlclient.service.ProductService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ProductController {
	
	@Autowired
	private ProductService productService;

	@GetMapping("/products")
	public Flux<Product> getProducts() {
		return productService.getProducts();
	}

	@PostMapping("/products/save")
	public Mono<Product> createProductUsingSaveAPI(@RequestBody Product product) {
		return productService.createProduct(product);
	}

	@GetMapping("/products/{productId}")
	public Mono<Product> getProduct(@PathVariable Integer productId, @RequestBody Product productRequest) {
		return productService.getProduct(productId);
	}

	@GetMapping("/products/delete/{productId}")
	public Mono<Void> deleteProduct(@PathVariable Integer productId) {
		return productService.deleteProduct(productId);
	}
	
	@GetMapping("/products/join/{productId}")
	public Mono<?> getProductDetailWithInventoryInfo(@PathVariable Integer productId) {
		return productService.retrieveProductWithInventoryInfo(productId);
	}

}
