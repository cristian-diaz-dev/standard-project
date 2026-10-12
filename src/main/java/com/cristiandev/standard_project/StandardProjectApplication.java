package com.cristiandev.standard_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.cristiandev.standard_project.restclient")
public class StandardProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(StandardProjectApplication.class, args);
	}

}
