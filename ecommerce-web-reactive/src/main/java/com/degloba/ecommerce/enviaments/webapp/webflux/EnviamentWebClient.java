package com.degloba.ecommerce.enviaments.webapp.webflux;


import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import com.degloba.ecommerce.enviaments.facade.dtos.EnviamentDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class EnviamentWebClient {

	   //WebClient client = WebClient.create("http://localhost:8080");   // port intern
	   WebClient client = WebClient.create("http://localhost:8888");   // port extern (Docker)
	    
	    public void consume() {

			/*
			 * Mono<EnviamentDto> enviamentMono = client.get() .uri("/enviaments/{id}", "1")
			 * .accept(MediaType.APPLICATION_JSON) .retrieve()
			 * .bodyToMono(EnviamentDto.class);
			 * 
			 * enviamentMono.subscribe(System.out::println);
			 */
	        
	        Flux<EnviamentDto> enviamentFlux = client.get()
	            .uri("/enviaments/")
	            .accept(MediaType.APPLICATION_JSON)
	            .retrieve()	           
	            .bodyToFlux(EnviamentDto.class);
	        
	        enviamentFlux.subscribe(System.out::println);
	    }
}
