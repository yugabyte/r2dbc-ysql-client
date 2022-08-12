package com.yugabyte.ysql.sample.r2dbcysqlclient.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("orders")
public class Order {

	@Id
	@Column("order_id")
	Long id;

	Integer productId;

	Integer quantity;

  @Column("order_date")
  @JsonFormat(pattern = "yyyy-MM-dd")
  LocalDate date;

}
