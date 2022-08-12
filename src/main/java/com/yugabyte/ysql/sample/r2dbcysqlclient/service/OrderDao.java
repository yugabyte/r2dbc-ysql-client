package com.yugabyte.ysql.sample.r2dbcysqlclient.service;

import com.yugabyte.ysql.sample.r2dbcysqlclient.domain.Order;
import com.yugabyte.ysql.sample.r2dbcysqlclient.domain.Product;
import com.yugabyte.ysql.sample.r2dbcysqlclient.repository.OrderRepository;
import com.yugabyte.ysql.sample.r2dbcysqlclient.repository.ProductRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class OrderDao {

  private final ProductRepository productRepository;
	private final OrderRepository orderRepository;

	public OrderDao(
    ProductRepository productRepository, OrderRepository orderRepository) {
    this.productRepository = productRepository;
    this.orderRepository = orderRepository;
  }

  // Example joining (1:M) tables
	public Mono<ProductAndOrder> retrieveProductWithInventoryInfo(Integer productId) {
    var product = productRepository.findById(productId);
    var orders = orderRepository.findAllByProductId(productId);
    return Flux
      .concat(product, orders)
      .collect(ProductAndOrder::new, (pao, object)->{
        if(object instanceof Product p){
          pao.setProduct(p);
        }else if (object instanceof Order o){
          pao.getOrders().add(o);
        }else{
          log.error("{}: Unsupported data type", object.getClass());
        }
      });
	}

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  public static class ProductAndOrder{
    Product product;
    List<Order> orders = new ArrayList<>(0);
  }

}
