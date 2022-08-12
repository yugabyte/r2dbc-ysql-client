package com.yugabyte.ysql.sample.r2dbcysqlclient.repository;

import com.yugabyte.ysql.sample.r2dbcysqlclient.domain.Order;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface OrderRepository  extends ReactiveCrudRepository<Order, Integer> {

  Flux<Order> findAllByProductId(Integer productId);
}
