package com.fabian.tutorial.microservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicate;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration 
public class ServicesRouterFunction {

	
	@Bean
	public RouterFunction<ServerResponse> routeRequest(ProductoService service){
		return RouterFunctions.route(RequestPredicates.GET("api/productos"), service::getAllProducts)
				.andRoute(RequestPredicates.POST("api/productos"), service::insertOne)
				.andRoute(RequestPredicates.GET("api/productos/ver/{id}"), service::getProduct);
	}
}
