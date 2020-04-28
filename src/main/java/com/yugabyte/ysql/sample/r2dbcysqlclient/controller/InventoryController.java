package com.yugabyte.ysql.sample.r2dbcysqlclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yugabyte.ysql.sample.r2dbcysqlclient.domain.Inventory;
import com.yugabyte.ysql.sample.r2dbcysqlclient.domain.Product;
import com.yugabyte.ysql.sample.r2dbcysqlclient.repository.InventoryRepository;

import reactor.core.publisher.Mono;

@RestController
public class InventoryController {
	
	@Autowired
	private InventoryRepository inventoryRepository;

	@PostMapping("/inventory/add")
	public Mono<Inventory> addInventoryDetailsForProduct(@RequestBody Inventory inventory) {
		return inventoryRepository.save(inventory);
	}

	@GetMapping("/inventory/get/{productId}")
	public Mono<Inventory> getInventoryById(@PathVariable Integer inventoryId, @RequestBody Product productRequest) {
		return inventoryRepository.findById(inventoryId);
	}

	@GetMapping("/inventory/delete/{productId}")
	public Mono<Void> deleteInventoryById(@PathVariable Integer inventoryId) {
		return inventoryRepository.deleteById(inventoryId);
	}

}
