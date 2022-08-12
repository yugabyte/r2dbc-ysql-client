package com.yugabyte.ysql.sample.r2dbcysqlclient.service;

import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;

@Component
public class InventoryDao {

	final DatabaseClient databaseClient;

	public InventoryDao(org.springframework.r2dbc.core.DatabaseClient databaseClient) {
		this.databaseClient = databaseClient;
	}

  // Example joining (1:1) tables
	public Mono<?> retrieveProductWithInventoryInfo(Integer productId) {

		return databaseClient
      .sql("""
        SELECT *
        FROM products p, inventory i
        WHERE
            p.product_id = :productId
            AND
            p.product_id = i.product_id
        LIMIT 1
      """)
      .bind("productId", productId)
      .fetch()
      .one();
	}

}
