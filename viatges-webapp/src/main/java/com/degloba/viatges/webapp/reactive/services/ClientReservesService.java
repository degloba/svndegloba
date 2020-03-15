package com.degloba.viatges.webapp.reactive.services;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.degloba.ecommerce.vendes.reserves.domain.persistence.rdbms.jpa.Reserva;
import com.degloba.viatges.webapp.controllers.rest.impl.spring.HotelsRestController;

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
public class ClientReservesService {

	public Flux<Reserva> buscarTotesReserves() {
		  
	    WebClient webclient = WebClient.create("http://localhost:8080/reserves");
	    
	    // simulem una lògica de negoci en la part web (front-End)
	    // accedint a diferents RestControllers (en aquest cas d'exemple es el mateix Rest)
	    Flux<Reserva> reserves1 = webclient.get().retrieve().bodyToFlux(Reserva.class);
	    Flux<Reserva> reserves2 = webclient.get().retrieve().bodyToFlux(Reserva.class);
	    Flux<Reserva> reserves3 = webclient.get().retrieve().bodyToFlux(Reserva.class);
	    
	    
	    Flux<Reserva> reserves = Flux.merge(reserves1,reserves2,reserves3);
	    System.out.println(reserves);
	    
	    return reserves;
	   }

	public Mono<Reserva> buscarReservaById(String id) {
		WebClient webclient = WebClient.create("http://localhost:8080/reserves/" + id);
		
		return webclient.get().retrieve().bodyToMono(Reserva.class);	
	}
	
	public Mono<Reserva> updateReserva(Reserva reserva)
    {
		WebClient webclient = WebClient.create("http://localhost:8080/reserves/1");
		
		Mono<Reserva> reservaData = webclient.get().retrieve().bodyToMono(Reserva.class);
		
		
		/*Reserva existingReserva= reservaData.geget(reserva.getAggregateId());
        if(existingReserva!=null)
        {
        	existingReserva.setActiu(true);
        }*/
        return null ;   ////Mono.just(existingReserva);
    }
}
