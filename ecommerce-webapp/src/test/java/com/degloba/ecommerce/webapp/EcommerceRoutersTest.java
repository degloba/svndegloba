package com.degloba.ecommerce.webapp;

import static org.mockito.BDDMockito.given;

import org.junit.Test;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.WebClient;

import com.degloba.ecommerce.enviaments.domain.persistence.rdbms.jpa.Enviament;
import com.degloba.ecommerce.enviaments.webapp.reactive.functional.ConfigurationRouterFunctionCompres;
import com.degloba.ecommerce.enviaments.webapp.reactive.service.ClientCompresService;
import com.degloba.ecommerce.vendes.compres.domain.persistence.rdbms.jpa.Compra;

import com.degloba.persistence.rdbms.jpa.AggregateId;

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
	
	ConfigurationRouterFunctionCompres config = new ConfigurationRouterFunctionCompres();
	
	ClientCompresService clientCompresService;
	
	@Test
	public void donadaCompraId_quanGetCompraById_llavorsCompraCorrecte() {
	    WebTestClient client = WebTestClient
	      .bindToRouterFunction(config.getCompraByIdRoute())
	      .build();
	 
	    Compra compra = new Compra(new AggregateId("1"));
	 
	    given(clientCompresService.buscarCompraById("1")).willReturn(Mono.just(compra));
	 
	    client.get()
	      .uri("/compres/1")
	      .exchange()
	      .expectStatus()
	      .isOk()
	      .expectBody(Compra.class)
	      .isEqualTo(compra);
	}
	
	@Test
	public void quanGetTotesCompres_llavorsCompresCorrectes() {
		
	    WebTestClient client = WebTestClient
	      .bindToRouterFunction(config.getTotesCompresRoute())
	      .build();
	 
	    List<Compra> compres = Arrays.asList(
	      new Compra(new AggregateId("1")),
	      new Compra(new AggregateId("2")));
	 
	    Flux<Compra> compraFlux = Flux.fromIterable(compres);
	    
	    given(clientCompresService.buscarTotesCompres()).willReturn(compraFlux);
	 
	    client.get()
	      .uri("/compres")
	      .exchange()
	      .expectStatus()
	      .isOk()
	      .expectBodyList(Compra.class)
	      .isEqualTo(compres);
	}
}
