package com.fabian.tutorial.microservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class ItemsRouterFunction {

	@Autowired
	private ItemsHandler handler;
	@Bean
	public RouterFunction<ServerResponse> router (){
		return RouterFunctions.route(RequestPredicates.GET("api/items"), handler::findAllProducts)
				.andRoute(RequestPredicates.POST("api/items"), handler::postProducto);
				
	}
	
	
}
