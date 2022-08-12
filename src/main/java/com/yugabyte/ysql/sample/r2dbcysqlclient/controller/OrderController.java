package com.yugabyte.ysql.sample.r2dbcysqlclient.controller;

import com.yugabyte.ysql.sample.r2dbcysqlclient.domain.Order;
import com.yugabyte.ysql.sample.r2dbcysqlclient.repository.OrderRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class OrderController {


	private final OrderRepository orderRepository;

  public OrderController(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }


  @PostMapping("/orders/add")
	public Mono<Order> add(@RequestBody Order order) {
		return orderRepository.save(order);
	}

	@GetMapping("/orders/get/{orderId}")
	public Mono<Order> get(@PathVariable Integer orderId) {
		return orderRepository.findById(orderId);
	}

	@GetMapping("/orders/delete/{orderId}")
	public Mono<Void> delete(@PathVariable Integer orderId) {
		return orderRepository.deleteById(orderId);
	}

}
