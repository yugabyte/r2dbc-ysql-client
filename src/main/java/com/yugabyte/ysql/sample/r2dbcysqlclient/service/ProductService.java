package com.yugabyte.ysql.sample.r2dbcysqlclient.service;

import org.springframework.stereotype.Service;

import com.yugabyte.ysql.sample.r2dbcysqlclient.domain.Product;
import com.yugabyte.ysql.sample.r2dbcysqlclient.repository.ProductRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {
	
	private final ProductRepository productRepository;
	private final InventoryDao inventoryDao;
	
	public ProductService(ProductRepository productRepository, InventoryDao inventoryDao) {
		this.productRepository = productRepository;
		this.inventoryDao = inventoryDao;
	}
	
	public Mono<Product> createProduct(Product product) {
		return productRepository.save(product);
	}
	
	public Flux<Product> getProducts() {
		return productRepository.findAll();
	}
	
	public Mono<Product> getProduct(Integer productId) {
		return productRepository.findById(productId);
	}
	
	public Mono<Void> deleteProduct(Integer productId) {
		return productRepository.deleteById(productId);
	}
	
	public Mono<?> retrieveProductWithInventoryInfo(Integer productId) {
		return inventoryDao.retrieveProductWithInventoryInfo(productId);
	}

}
