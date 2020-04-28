package com.yugabyte.ysql.sample.r2dbcysqlclient.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.yugabyte.ysql.sample.r2dbcysqlclient.domain.Inventory;

public interface InventoryRepository extends ReactiveCrudRepository<Inventory, Integer> {

}
