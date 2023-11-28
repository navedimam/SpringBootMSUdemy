package com.config.servver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigServverApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigServverApplication.class, args);
	}

}
