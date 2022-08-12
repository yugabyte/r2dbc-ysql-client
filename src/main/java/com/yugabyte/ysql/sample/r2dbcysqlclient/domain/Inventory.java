package com.yugabyte.ysql.sample.r2dbcysqlclient.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("inventory")
public class Inventory {

	@Id
	@Column(value = "inventory_id")
	Integer id;

	Integer productId;

	Integer inventoryCount;

}
