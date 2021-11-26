package com.fabian.tutorial.microservice;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.fabian.tutorial.microservice.models.Producto;
import com.fabian.tutorial.microservice.repositories.ProductoRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ProductoService {
	
	@Value("${server.port}")
	private String port;
	@Autowired
	private ProductoRepository repository;
	public Mono<ServerResponse> getAllProducts (ServerRequest r) { 
		/*return ServerResponse.ok().body(repository.findAll(), Producto.class)
				.switchIfEmpty(ServerResponse.noContent().build())
				.onErrorResume(error->{
					return ServerResponse.badRequest().body(error.getMessage(), String.class);
				});*/
		
		return repository.findAll()
				.doOnNext(p->System.out.println("encontre una instancia"))
				.collectList()
				.flatMap(lP->{
					if (lP.isEmpty()) {
						return Mono.error( new InterruptedException("error simulado"));
						//return ServerResponse.noContent().build();
					}
					else {
						lP.forEach(p->p.setPort(port));
						return ServerResponse.ok().body(BodyInserters.fromObject(lP));
					}
				})
				.onErrorResume(error->{
					return ServerResponse.badRequest().body(Mono.just(error.getMessage()), String.class);
				});
				
		
		
	}
	
	public Mono<ServerResponse> insertOne(ServerRequest request){
		Mono<Producto> producto = request.bodyToMono(Producto.class);
		return producto.doOnNext(System.out::println).flatMap(p-> repository.save(p))
				.flatMap(p->ServerResponse.created(URI.create("api/productos")).body(Mono.just(p), Producto.class));
		
	}
}
