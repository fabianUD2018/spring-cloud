package com.fabian.tutorial.microservice.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.fabian.tutorial.microservice.models.Producto;

public interface ProductoRepository extends ReactiveMongoRepository<Producto, String>{

}
