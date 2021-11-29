package com.fabian.tutorial.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class SpringCloudServiceItemApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudServiceItemApplication.class, args);
	}

}
