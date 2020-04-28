package com.yugabyte.ysql.sample.r2dbcysqlclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableWebFlux
public class R2dbcYsqlClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(R2dbcYsqlClientApplication.class, args);
	}
}
