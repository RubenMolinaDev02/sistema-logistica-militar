package com.example.location_microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@EnableMethodSecurity
@SpringBootApplication
public class LocationMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LocationMicroserviceApplication.class, args);
	}

}
