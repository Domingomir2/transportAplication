package com.transport.transportApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class TransportApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransportApplication.class, args);
	}

}
