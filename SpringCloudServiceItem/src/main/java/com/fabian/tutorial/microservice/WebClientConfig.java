package com.fabian.tutorial.microservice;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

	
	@Bean
	@LoadBalanced
	public WebClient.Builder client() {
		
		return WebClient.builder();
	}
}
