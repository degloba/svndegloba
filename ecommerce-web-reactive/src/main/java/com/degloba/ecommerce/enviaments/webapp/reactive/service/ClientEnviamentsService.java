package com.degloba.enviaments.webapp.reactive.service;


import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.degloba.ecommerce.enviaments.facade.dtos.EnviamentDto;
import com.degloba.hotels.HotelDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @category client del {@link EnviamentsRestController} que utilitza {@link WebClient} per poder fer requests HTTP
 * no-bloquejants (s'executen en paralel!!!!)
 * 
 * @category encapsula l'execució de peticions http/Rest en paralel
 * 
 * @author degloba
 *
 */
@Service
public class ClientEnviamentsService {

	public Flux<EnviamentDto> buscarTotsEnviaments() {
		  
	    WebClient webclient = WebClient.create("http://localhost:8080/enviaments/");
	    
	    // simulem una lògica de negoci en la part web (front-End)
	    // accedint a diferents RestControllers (en aquest cas d'exemple es el mateix Rest)
	    Flux<EnviamentDto> fluxEnviament1 = webclient.get().accept(MediaType.APPLICATION_JSON).retrieve().bodyToFlux(EnviamentDto.class);
	    Flux<EnviamentDto> fluxEnviament2 = webclient.get().accept(MediaType.APPLICATION_JSON).retrieve().bodyToFlux(EnviamentDto.class);
	    Flux<EnviamentDto> fluxEnviament3 = webclient.get().accept(MediaType.APPLICATION_JSON).retrieve().bodyToFlux(EnviamentDto.class);
	    
	    
	    Flux<EnviamentDto> fluxEnviaments = Flux.merge(fluxEnviament1,fluxEnviament2,fluxEnviament3);
	    System.out.println(fluxEnviaments);
	    
	    return fluxEnviaments;
	   }

	public Mono<EnviamentDto> buscarEnviamentById(String id) {
		WebClient webclient = WebClient.create("http://localhost:8080/enviaments/" + id);
		
		return webclient.get().retrieve().bodyToMono(EnviamentDto.class);	
	}
	
	public Mono<EnviamentDto> updateEnviament(EnviamentDto enviament)
    {
		WebClient webclient = WebClient.create("http://localhost:8080/enviaments/1");
		
		Mono<EnviamentDto> enviamentData = webclient.get().retrieve().bodyToMono(EnviamentDto.class);
		
		/*CompraDto existingCompra = hotelData.get(compra.getAggregateId());
        if(existingCompra!=null)
        {
        	existingCompra.setActiu(true);
        }*/
        return null ;   ////Mono.just(existingCompra);
    }
}