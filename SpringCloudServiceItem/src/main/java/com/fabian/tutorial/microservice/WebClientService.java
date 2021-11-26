package com.fabian.tutorial.microservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fabian.tutorial.microservice.dtos.Producto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class WebClientService {
	
	@Autowired
	private WebClient.Builder client;
	
	
	public  Flux<Producto>	 findAllProducts(){
		return client.build().get()
				.uri("http://productos-service/api/productos")
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToFlux(Producto.class);
		//.exchangeToFlux(r->r.bodyToFlux(Producto.class));
	}
	
	public Mono<Producto> saveProduct(Producto p){
		return client
				.build()
				.post()
				.uri("http://productos-service/api/products")
				.bodyValue(p)
				.retrieve()
				.bodyToMono(Producto.class);
	}

}
