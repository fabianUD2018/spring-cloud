package com.fabian.tutorial.microservice.dtos;

public class Item {

	private Producto producto;
	private Integer cantidad;
	public Item(Producto producto, Integer cantidad) {
		super();
		this.producto = producto;
		this.cantidad = cantidad;
		this.total = producto.getPrecio().intValue() * cantidad;
	}

	private Integer total;
	
	
	
	public Producto getProducto() {
		return producto;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	} 
	
	
}
