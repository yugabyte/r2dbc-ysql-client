package com.yugabyte.ysql.sample.r2dbcysqlclient.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("inventory")
public class Inventory {
	
	@Id
	@Column(value = "inventory_id")
	Integer id;
	
	@Column(value = "product_id")
	Integer productId;
	
	@Column(value = "inventory_count")
	Integer inventoryCount;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getQuantity() {
		return inventoryCount;
	}

	public void setQuantity(Integer inventoryCount) {
		this.inventoryCount = inventoryCount;
	}
	
}
