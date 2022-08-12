package com.yugabyte.ysql.sample.r2dbcysqlclient.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("products")
public class Product {

  @Id
  private Integer productId;

  private String productName;

  private String description;

  private double price;

}
