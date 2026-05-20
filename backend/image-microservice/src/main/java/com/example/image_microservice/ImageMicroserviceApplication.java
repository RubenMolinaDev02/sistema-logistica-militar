package com.example.image_microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@EnableMethodSecurity
@SpringBootApplication
public class ImageMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImageMicroserviceApplication.class, args);
	}

}
