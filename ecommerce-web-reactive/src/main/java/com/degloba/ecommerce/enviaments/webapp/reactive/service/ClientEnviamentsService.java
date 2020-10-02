package com.degloba.ecommerce.enviaments.webapp.reactive.service;


import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

//////import com.degloba.ecommerce.enviaments.domain.persistence.nosql.mongo.Enviament;
import com.degloba.ecommerce.enviaments.facade.dtos.EnviamentDto;

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
		  
	    WebClient webclient = WebClient.create("http://localhost:8080/enviaments/");  // port intern (no Docker) , ecommerce-webapp
		//WebClient webclient = WebClient.create("http://localhost:8888/enviaments/");  // port extern (Docker) , ecommerce-webapp
	    
	    // simulem una lògica de negoci en la part web (front-End)
	    // accedint a diferents RestControllers (en aquest cas d'exemple es el mateix Rest)
	    Flux<EnviamentDto> fluxEnviament1 = webclient.get().accept(MediaType.APPLICATION_JSON).retrieve().bodyToFlux(EnviamentDto.class);
	    Flux<EnviamentDto> fluxEnviament2 = webclient.get().accept(MediaType.APPLICATION_JSON).retrieve().bodyToFlux(EnviamentDto.class);
	    Flux<EnviamentDto> fluxEnviament3 = webclient.get().accept(MediaType.APPLICATION_JSON).retrieve().bodyToFlux(EnviamentDto.class);
	    
	  	    
	    // ************  aqui convertim EntitatS a DTOs
	    	    
	    Flux<EnviamentDto> fluxEnviaments = Flux.merge(fluxEnviament1,fluxEnviament2,fluxEnviament3);
	    
	    return fluxEnviaments;    // si els diferents webservices ens tornen DTOs
	    /////return fluxEnviaments.flatMap(UserrService::covertUserDAOToBUserBO);
	    
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