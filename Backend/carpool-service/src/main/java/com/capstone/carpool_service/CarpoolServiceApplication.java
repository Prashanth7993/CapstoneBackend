package com.capstone.carpool_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CarpoolServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarpoolServiceApplication.class, args);
	}

}
