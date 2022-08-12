package com.yugabyte.ysql.sample.r2dbcysqlclient.config;

import com.yugabyte.ysql.sample.r2dbcysqlclient.repository.ProductRepository;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.r2dbc.core.DatabaseClient;

@Configuration
@EnableR2dbcRepositories(basePackageClasses = ProductRepository.class)
public class ReactiveDataSourceConfig  {
  @Bean
  DatabaseClient databaseClient(ConnectionFactory connectionFactory) {
    return DatabaseClient.builder()
      .connectionFactory(connectionFactory)
      //.bindMarkers(() -> BindMarkersFactory.named(":", "", 20).create())
      .namedParameters(true)
      .build();
  }
}
