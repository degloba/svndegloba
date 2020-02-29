package com.degloba.ecommerce;



import java.util.Collections;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import com.degloba.ecommerce.enviaments.domain.persistence.rdbms.jpa.Enviament;
import com.degloba.persistence.rdbms.jpa.AggregateId;

import reactor.core.publisher.Mono;

/**
 * @category Test Web Layer + Mock Backend (Service Layer)
 * 
 * @see https://www.baeldung.com/spring-mocking-webclient
 * 
 * @author degloba
 *
 */
public class EnviamentService {
	
	private String _baseUrl; 
	
	public EnviamentService(String baseUrl) {
		// TODO Auto-generated constructor stub
		_baseUrl = baseUrl;
	}

    WebClient webClient = WebClient.builder().build();
    
	/*public Mono<Enviament> getEnviamentById(Integer enviamentId) {
		
       WebClient webClient = WebClient
		  .builder()
		    .baseUrl(_baseUrl)
		    .defaultCookie("cookieKey", "cookieValue")
		    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE) 
		    .defaultUriVariables(Collections.singletonMap("url", "http://localhost:8080"))
		  .build();
	   */
    		  
       public Mono<Enviament> getEnviamentById(AggregateId enviamentId) {
           return webClient
                   .get()
                   .uri(_baseUrl + "/enviament/{id}", enviamentId.getAggregateId())
                   .retrieve()
                   .bodyToMono(Enviament.class);
        
    }
}
