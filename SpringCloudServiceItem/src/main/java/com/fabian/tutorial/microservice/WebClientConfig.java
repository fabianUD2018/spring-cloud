package com.fabian.tutorial.microservice;

import java.time.Duration;

import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;

@Configuration
public class WebClientConfig {

	
	@Bean
	@LoadBalanced
	public WebClient.Builder client() {
		
		return WebClient.builder();
	}
	
	@Bean
	public Customizer<Resilience4JCircuitBreakerFactory> defaultCustomizer(){
		return factory->factory.configureDefault(id->{
			return new Resilience4JConfigBuilder(id)
					.circuitBreakerConfig(CircuitBreakerConfig.custom()
					.slidingWindowSize(10)//ventana de errores
					.failureRateThreshold(50)//magen de error
					.waitDurationInOpenState(Duration.ofMillis(10000))//tiempo en el estado abierto
					.build())
					//timeLimiterConfig(TimeLimiterConfig.ofDefaults())
					.timeLimiterConfig(TimeLimiterConfig.custom()
							.timeoutDuration(Duration.ofSeconds(2))
							.build())
					.build();
		});
	}
}
