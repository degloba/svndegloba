package com.degloba.ecommerce.enviaments.webapp.controllers.impl.spring;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

public class EnviamentWebFluxHandler {
	
	public Mono<ServerResponse> hello(ServerRequest request) {
		return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN)
			.body(BodyInserters.fromValue("Hello, Spring!"));
	}
}
