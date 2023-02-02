package com.reactive.kafkapoc1;

import io.r2dbc.spi.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;


@SpringBootApplication
public class Kafkapoc1Application {

	private static final Logger log = LoggerFactory.getLogger(Kafkapoc1Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Kafkapoc1Application.class, args);
	}

}
