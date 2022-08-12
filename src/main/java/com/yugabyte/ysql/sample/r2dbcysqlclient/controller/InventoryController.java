package com.yugabyte.ysql.sample.r2dbcysqlclient.controller;

import com.yugabyte.ysql.sample.r2dbcysqlclient.domain.Inventory;
import com.yugabyte.ysql.sample.r2dbcysqlclient.repository.InventoryRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class InventoryController {

	private final InventoryRepository inventoryRepository;

  public InventoryController(InventoryRepository inventoryRepository) {
    this.inventoryRepository = inventoryRepository;
  }

  @PostMapping("/inventory/add")
	public Mono<Inventory> add(@RequestBody Inventory inventory) {
		return inventoryRepository.save(inventory);
	}

	@GetMapping("/inventory/get/{inventoryId}")
	public Mono<Inventory> get(@PathVariable Integer inventoryId) {
		return inventoryRepository.findById(inventoryId);
	}

	@GetMapping("/inventory/delete/{inventoryId}")
	public Mono<Void> delete(@PathVariable Integer inventoryId) {
		return inventoryRepository.deleteById(inventoryId);
	}

}
