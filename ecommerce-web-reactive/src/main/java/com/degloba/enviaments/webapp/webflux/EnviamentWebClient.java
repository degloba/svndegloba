package com.degloba.hotels.webapp.webflux;


import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class HotelWebClient {

	   WebClient client = WebClient.create("http://localhost:8080");
	    
	    public void consume() {

	        Mono<Hotel> employeeMono = client.get()
	            .uri("/employees/{id}", "1")
	            .retrieve()
	            .bodyToMono(Hotel.class);

	        employeeMono.subscribe(System.out::println);
	        
	        Flux<Hotel> employeeFlux = client.get()
	            .uri("/employees")
	            .retrieve()
	            .bodyToFlux(Hotel.class);
	        
	        employeeFlux.subscribe(System.out::println);
	    }
}
