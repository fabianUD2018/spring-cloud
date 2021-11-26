package com.fabian.tutorial.microservice.dtos;

import java.util.Date;


public class Producto {

	
	private String id;
	private String nombre;
	private Double precio;
	private Date creadoEl;
	private String port;

	
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
	
	
	
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Producto [id=");
		builder.append(id);
		builder.append(", nombre=");
		builder.append(nombre);
		builder.append(", precio=");
		builder.append(precio);
		builder.append(", creadoEl=");
		builder.append(creadoEl);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
