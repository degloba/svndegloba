package com.degloba.ecommerce.webapp;

import static org.mockito.BDDMockito.given;

import org.junit.Test;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.WebClient;

import com.degloba.ecommerce.enviaments.domain.persistence.rdbms.jpa.Enviament;
import com.degloba.ecommerce.vendes.reserves.domain.persistence.rdbms.jpa.Reserva;
import com.degloba.persistence.rdbms.jpa.AggregateId;
import com.degloba.viatges.webapp.services.ClientReservesService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

/**
 * @category Test de les {@link org.springframework.web.reactive.function.server.RouterFunction}
 * 
 * @author degloba
 *
 */
public class EcommerceRoutersTest {
	
	ConfigurationRoutersEcommerce config = new ConfigurationRoutersEcommerce();
	
	ClientReservesService clientReservesService;
	
	@Test
	public void donadaReservaId_quanGetReservaById_llavorsReservaCorrecte() {
	    WebTestClient client = WebTestClient
	      .bindToRouterFunction(config.getReservaByIdRoute())
	      .build();
	 
	    Reserva reserva = new Reserva(new AggregateId("1"));
	 
	    given(clientReservesService.buscarReservaById("1")).willReturn(Mono.just(reserva));
	 
	    client.get()
	      .uri("/reserves/1")
	      .exchange()
	      .expectStatus()
	      .isOk()
	      .expectBody(Reserva.class)
	      .isEqualTo(reserva);
	}
	
	@Test
	public void quanGetTotesReseves_llavorsReservesCorrectes() {
		
	    WebTestClient client = WebTestClient
	      .bindToRouterFunction(config.getAllReservesRoute())
	      .build();
	 
	    List<Reserva> reserves = Arrays.asList(
	      new Reserva(new AggregateId("1")),
	      new Reserva(new AggregateId("2")));
	 
	    Flux<Reserva> reservaFlux = Flux.fromIterable(reserves);
	    
	    given(clientReservesService.buscarTotesReserves()).willReturn(reservaFlux);
	 
	    client.get()
	      .uri("/reserves")
	      .exchange()
	      .expectStatus()
	      .isOk()
	      .expectBodyList(Reserva.class)
	      .isEqualTo(reserves);
	}
}
