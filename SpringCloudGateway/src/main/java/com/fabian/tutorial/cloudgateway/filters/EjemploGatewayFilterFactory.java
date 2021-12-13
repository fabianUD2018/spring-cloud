package com.fabian.tutorial.cloudgateway.filters;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;

@Component
public class EjemploGatewayFilterFactory extends AbstractGatewayFilterFactory<EjemploGatewayFilterFactory.Configuracion> {
	Logger logger = LoggerFactory.getLogger(EjemploGatewayFilterFactory.class);

	public static class Configuracion {

	}
	
	private String mensaje;
	private String usuario;
	
	

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	@Override
	public GatewayFilter apply(Configuracion config) {
		// TODO Auto-generated method stub
		return (exchange, chain)-> {
			logger.info("ejecutando pre ");
			return chain.filter(exchange).then(Mono.fromRunnable(()->logger.info("ejecutando post ")));
		};
	}

	@Override
	public ShortcutType shortcutType() {
		// TODO Auto-generated method stub
		return super.shortcutType();
	}

	@Override
	public List<String> shortcutFieldOrder() {
		// TODO Auto-generated method stub
		return Arrays.asList("mensaje", "usuario");
	}

	@Override
	public String shortcutFieldPrefix() {
		// TODO Auto-generated method stub
		return super.shortcutFieldPrefix();
	}

	
	
}
