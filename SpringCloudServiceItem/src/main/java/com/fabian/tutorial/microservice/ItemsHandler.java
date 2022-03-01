package com.fabian.tutorial.microservice;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.fabian.tutorial.microservice.dtos.Item;
import com.fabian.tutorial.microservice.dtos.Producto;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import reactor.core.publisher.Mono;

@RefreshScope
@Component
public class ItemsHandler {

	@Autowired
	private CircuitBreakerFactory cbFactory;
	
	@Autowired
	private WebClientService service;
	
	@Value("${custom.message}")
	private String messageConfiguration;

	public Mono<ServerResponse> findAllProducts(ServerRequest r){
		return service.findAllProducts()
				.doOnNext(System.out::println)
				.collectList().flatMap(l ->{
				List<Item> items = 			l.stream().map(p->new Item(p, 4)).collect(Collectors.toList());
				return ServerResponse.ok().body(Mono.just(items), Producto.class);
		});
	}

	//@HystrixCommand(fallbackMethod = "some_method")
	public Mono<ServerResponse> postProducto(ServerRequest r) {
		Mono<Producto> producto = r.bodyToMono(Producto.class);
		return cbFactory.create("items").run(()->producto.flatMap(p->service.saveProduct(p))
				.flatMap(p -> ServerResponse.created(URI.create("api/productos")).body(Mono.just(p), Producto.class))
				.switchIfEmpty(ServerResponse.badRequest().build()),
				e-> ServerResponse.badRequest().body(Mono.just(e.getMessage()), String.class));
		
	}
	
	@CircuitBreaker(name="items", fallbackMethod = "metodo_alternativo")
	public Mono<ServerResponse> postProductoV2(ServerRequest r) {
		Mono<Producto> producto = r.bodyToMono(Producto.class);
		return producto.flatMap(p->service.saveProduct(p))
				.flatMap(p -> ServerResponse.created(URI.create("api/productos")).body(Mono.just(p), Producto.class))
				.switchIfEmpty(ServerResponse.badRequest().build());
		
	}
	
	
	public Mono<ServerResponse> obtainConfiguration(ServerRequest request){
		
		Map<String, String > response = new HashMap<>();
		response.put("config-information", messageConfiguration);
		
		return ServerResponse.ok().body(Mono.just(response), HashMap.class);
	}
}
