package com.fabian.tutorial.microservice.models;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection ="productos")
public class Producto {

	@Id
	private String id;
	private String nombre;
	private Double precio;
	@Field(name = "creado_el")
	private Date creadoEl;
	
	@Transient
	private String port;
	
	
	
	
	
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public Producto() {
		super();
	}
	public Producto(String nombre, Double precio, Date creadoEl) {
		super();
		this.nombre = nombre;
		this.precio = precio;
		this.creadoEl = creadoEl;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	public Date getCreadoEl() {
		return creadoEl;
	}
	public void setCreadoEl(Date creadoEl) {
		this.creadoEl = creadoEl;
	}
	
	
	
}
