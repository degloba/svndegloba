package com.degloba.ecommerce.enviaments.webapp.reactive.service;


import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.degloba.ecommerce.vendes.compres.facade.dtos.CompraDto;


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

	public Flux<CompraDto> buscarTotesCompres() {
		  
	    WebClient webclient = WebClient.create("http://localhost:8080/compres");
	    
	    // simulem una lògica de negoci en la part web (front-End)
	    // accedint a diferents RestControllers (en aquest cas d'exemple es el mateix Rest)
	    Flux<CompraDto> compres1 = webclient.get().retrieve().bodyToFlux(CompraDto.class);
	    Flux<CompraDto> compres2 = webclient.get().retrieve().bodyToFlux(CompraDto.class);
	    Flux<CompraDto> compres3 = webclient.get().retrieve().bodyToFlux(CompraDto.class);
	    
	    
	    Flux<CompraDto> compres = Flux.merge(compres1,compres2,compres3);
	    System.out.println(compres);
	    
	    return compres;
	   }

	public Mono<CompraDto> buscarCompraById(String id) {
		WebClient webclient = WebClient.create("http://localhost:8080/compres/" + id);
		
		return webclient.get().retrieve().bodyToMono(CompraDto.class);	
	}
	
	public Mono<CompraDto> updateCompra(CompraDto compra)
    {
		WebClient webclient = WebClient.create("http://localhost:8080/compres/1");
		
		Mono<CompraDto> compraData = webclient.get().retrieve().bodyToMono(CompraDto.class);
		
		
		/*CompraDto existingCompra = compraData.get(compra.getAggregateId());
        if(existingCompra!=null)
        {
        	existingCompra.setActiu(true);
        }*/
        return null ;   ////Mono.just(existingCompra);
    }
}