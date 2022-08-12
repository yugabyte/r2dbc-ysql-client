package com.yugabyte.ysql.sample.r2dbcysqlclient.controller;

import com.yugabyte.ysql.sample.r2dbcysqlclient.service.OrderDao.ProductAndOrder;
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

	private final ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping("/products")
	public Flux<Product> getProducts() {
		return productService.getProducts();
	}

	@PostMapping("/products/add")
	public Mono<Product> add(@RequestBody Product product) {
		return productService.createProduct(product);
	}

	@GetMapping("/products/{productId}")
	public Mono<Product> get(@PathVariable Integer productId) {
		return productService.getProduct(productId);
	}

	@GetMapping("/products/delete/{productId}")
	public Mono<Void> delete(@PathVariable Integer productId) {
		return productService.deleteProduct(productId);
	}

	@GetMapping("/products/with-inventory/{productId}")
	public Mono<?> getWithInventory(@PathVariable Integer productId) {
		return productService.retrieveProductWithInventoryInfo(productId);
	}

  @GetMapping("/products/with-orders/{productId}")
  public Mono<ProductAndOrder> getWithOrders(@PathVariable Integer productId){
    return productService.getProductWithOrders(productId);
  }
}
