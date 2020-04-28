package com.yugabyte.ysql.sample.r2dbcysqlclient.service;

import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;

@Component
public class InventoryDao {

	final DatabaseClient databaseClient;

	public InventoryDao(DatabaseClient databaseClient) {
		this.databaseClient = databaseClient;
	}

	public Mono<?> retrieveProductWithInventoryInfo(Integer productId) {

		return databaseClient.execute("SELECT * from products p, inventory i " + 
				"where p.product_id =" + productId
				+ " AND p.product_id = i.product_id")
				.fetch()
				.one();
	}

}
