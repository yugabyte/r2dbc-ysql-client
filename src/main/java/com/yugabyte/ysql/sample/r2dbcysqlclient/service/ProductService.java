package com.yugabyte.ysql.sample.r2dbcysqlclient.service;

import com.yugabyte.ysql.sample.r2dbcysqlclient.service.OrderDao.ProductAndOrder;
import org.springframework.stereotype.Service;

import com.yugabyte.ysql.sample.r2dbcysqlclient.domain.Product;
import com.yugabyte.ysql.sample.r2dbcysqlclient.repository.ProductRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {

	private final ProductRepository productRepository;
	private final InventoryDao inventoryDao;

  private final OrderDao orderDao;

	public ProductService(ProductRepository productRepository, InventoryDao inventoryDao,
    OrderDao orderDao) {
		this.productRepository = productRepository;
		this.inventoryDao = inventoryDao;
    this.orderDao = orderDao;
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

  public Mono<ProductAndOrder> getProductWithOrders(Integer productId){
    return orderDao.retrieveProductWithInventoryInfo(productId);

  }
}
