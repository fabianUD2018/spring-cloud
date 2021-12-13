package com.fabian.tutorial.cloudgateway.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class GlobalFilter implements org.springframework.cloud.gateway.filter.GlobalFilter{
	Logger loger = LoggerFactory.getLogger(GlobalFilter.class);
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
			loger.info("ejecutando pre filtro");
			exchange.getRequest().mutate().headers(h-> h.add("token", "1234"));
		return chain.filter(exchange).then(Mono.fromRunnable(()->{
			loger.info("ejecutando post filtro");
			exchange.getResponse().getCookies().add("color", ResponseCookie.from("color", "rojo").build());
			exchange.getResponse().getHeaders().setContentType(MediaType.TEXT_PLAIN);
		}));
	}

}
