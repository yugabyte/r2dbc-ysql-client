package com.yugabyte.ysql.sample.r2dbcysqlclient.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

import com.yugabyte.ysql.sample.r2dbcysqlclient.repository.ProductRepository;

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;

@Configuration
@EnableR2dbcRepositories(basePackageClasses = ProductRepository.class)
public class ReactiveDataSourceConfig extends AbstractR2dbcConfiguration {

	@Value("${yugabyte.ysql.datasource.host:localhost}")
	private String host;
	@Value("${yugabyte.ysql.datasource.port:5433}")
	private int port;
	@Value("${yugabyte.ysql.datasource.database:yugabyte}")
	private String database;
	@Value("${datasource.username:yugabyte}")
	private String username;
	
	@Bean(name = "r2dbcConnection")
	@Override
	public PostgresqlConnectionFactory connectionFactory() {
		
		return new PostgresqlConnectionFactory(PostgresqlConnectionConfiguration
				.builder()
				.host(host)
				.port(port)
				.username(username)
				.database(database)
				.build());	
	}

}
