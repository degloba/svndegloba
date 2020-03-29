package com.degloba.ecommerce.webapp;

import static org.mockito.BDDMockito.given;

import org.junit.Test;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.WebClient;

import com.degloba.ecommerce.enviaments.webapp.reactive.functional.ConfigurationRouterFunctionCompres;
import com.degloba.ecommerce.enviaments.webapp.reactive.service.ClientCompresService;
import com.degloba.ecommerce.vendes.compres.facade.dtos.CompraDto;

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
	
	//ClientCompresService clientCompresService;
	
	@Test
	public void donadaCompraId_quanGetCompraById_llavorsCompraCorrecte() {
	    WebTestClient client = WebTestClient
	      .bindToRouterFunction(config.getCompraByIdRoute())
	      .build();
	 
	    CompraDto compra = new CompraDto("1");
	 
	    given(config.clientCompresService().buscarCompraById("1")).willReturn(Mono.just(compra));
	 
	    client.get()
	      .uri("/compres/1")
	      .exchange()
	      .expectStatus()
	      .isOk()
	      .expectBody(CompraDto.class)
	      .isEqualTo(compra);
	}
	
	@Test
	public void quanGetTotesCompres_llavorsCompresCorrectes() {
		
	    WebTestClient client = WebTestClient
	      .bindToRouterFunction(config.getTotesCompresRoute())
	      .build();
	 
	    List<CompraDto> compres = Arrays.asList(
	      new CompraDto("1"),
	      new CompraDto("2"),
	      new CompraDto("3"));
	 
	    Flux<CompraDto> compraFlux = Flux.fromIterable(compres);
	    
	    given(config.clientCompresService().buscarTotesCompres()).willReturn(compraFlux);
	 
	    client.get()
	      .uri("/compres")
	      .exchange()
	      .expectStatus()
	      .isOk()
	      .expectBodyList(CompraDto.class)
	      .isEqualTo(compres);
	}
}
