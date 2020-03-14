package com.degloba.ecommerce.enviaments.webapp.reactive.service;


import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.degloba.ecommerce.vendes.compres.domain.persistence.rdbms.jpa.Compra;
import com.degloba.ecommerce.vendes.reserves.domain.persistence.rdbms.jpa.Reserva;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @category client del {@link HotelsRestController} que utilitza {@link WebClient} per poder fer requests HTTP
 * no-bloquejants (s'executen en paralel!!!!)
 * 
 * @category encapsula l'execució de peticions http/Rest en paralel
 * 
 * @author degloba
 *
 */
@Service
public class ClientCompresService {

	public static Flux<Compra> buscarTotesCompres() {
		  
	    WebClient webclient = WebClient.create("http://localhost:8080/compres");
	    
	    // simulem una lògica de negoci en la part web (front-End)
	    // accedint a diferents RestControllers (en aquest cas d'exemple es el mateix Rest)
	    Flux<Compra> compres1 = webclient.get().retrieve().bodyToFlux(Compra.class);
	    Flux<Compra> compres2 = webclient.get().retrieve().bodyToFlux(Compra.class);
	    Flux<Compra> compres3 = webclient.get().retrieve().bodyToFlux(Compra.class);
	    
	    
	    Flux<Compra> compres = Flux.merge(compres1,compres2,compres3);
	    System.out.println(compres);
	    
	    return compres;
	   }

	public static Mono<Compra> buscarCompraById(String id) {
		WebClient webclient = WebClient.create("http://localhost:8080/compres/" + id);
		
		return webclient.get().retrieve().bodyToMono(Compra.class);	
	}
	
	public static Mono<Reserva> updateCompra(Compra compra)
    {
		WebClient webclient = WebClient.create("http://localhost:8080/compres/1");
		
		Mono<Compra> compraData = webclient.get().retrieve().bodyToMono(Compra.class);
		
		
		/*Compra existingCompra= compraData.get(compra.getAggregateId());
        if(existingCompra!=null)
        {
        	existingCompra.setActiu(true);
        }*/
        return null ;   ////Mono.just(existingCompra);
    }
}