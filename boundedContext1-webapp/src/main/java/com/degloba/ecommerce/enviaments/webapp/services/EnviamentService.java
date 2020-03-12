package com.degloba.ecommerce.enviaments.webapp.services;

import java.net.URI;
import java.util.Collections;
import java.util.List;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.springframework.binding.collection.MapAccessor;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ReactiveHttpOutputMessage;
import org.springframework.http.client.reactive.ClientHttpRequest;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.degloba.ecommerce.enviaments.domain.persistence.rdbms.jpa.Enviament;
import com.degloba.ecommerce.vendes.reserves.domain.persistence.rdbms.jpa.Reserva;
import com.degloba.persistence.rdbms.jpa.AggregateId;

import reactor.core.publisher.Mono;
import org.springframework.util.LinkedMultiValueMap;
import reactor.util.MultiValueMap;


/**
 * @category Test Web Layer + Mock Backend (Service Layer)
 * 
 * @see https://www.baeldung.com/spring-mocking-webclient
 * @see https://www.baeldung.com/spring-5-webclient
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

	public Mono<Enviament> getEnviamentById(AggregateId id) {
		
		 WebClient webclient = WebClient.create(_baseUrl + id);
		 
		 return webclient.get().retrieve().bodyToMono(Enviament.class);
	}

}
