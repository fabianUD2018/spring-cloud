package com.fabian.tutorial.microservice;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.fabian.tutorial.microservice.dtos.Item;
import com.fabian.tutorial.microservice.dtos.Producto;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import reactor.core.publisher.Mono;

@Component
public class ItemsHandler {

	@Autowired
	private WebClientService service;

	public Mono<ServerResponse> findAllProducts(ServerRequest r){
		return service.findAllProducts()
				.doOnNext(System.out::println)
				.collectList().flatMap(l ->{
				List<Item> items = 			l.stream().map(p->new Item(p, 4)).collect(Collectors.toList());
				return ServerResponse.ok().body(Mono.just(items), Producto.class);
		});
	}

	@HystrixCommand(fallbackMethod = "some_method")
	public Mono<ServerResponse> postProducto(ServerRequest r) {
		Mono<Producto> producto = r.bodyToMono(Producto.class);
		
		return producto.flatMap(p->service.saveProduct(p))
				.flatMap(p -> ServerResponse.created(URI.create("api/products")).body(Mono.just(p), Producto.class))
				.switchIfEmpty(ServerResponse.badRequest().build());
	}
}
