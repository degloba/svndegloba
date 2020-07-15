package com.degloba.hotels.webapp.reactive.service;


import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.degloba.hotels.HotelDto;

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
//@Service
public class ClientHotelsService {

	public Flux<HotelDto> buscarTotsHotels() {
		  
	    WebClient webclient = WebClient.create("http://localhost:8080/hotels");
	    
	    // simulem una lògica de negoci en la part web (front-End)
	    // accedint a diferents RestControllers (en aquest cas d'exemple es el mateix Rest)
	    Flux<HotelDto> fluxHotel1 = webclient.get().retrieve().bodyToFlux(HotelDto.class);
	    Flux<HotelDto> fluxHotel2 = webclient.get().retrieve().bodyToFlux(HotelDto.class);
	    Flux<HotelDto> fluxHotel3 = webclient.get().retrieve().bodyToFlux(HotelDto.class);
	    
	    
	    Flux<HotelDto> fluxHotels = Flux.merge(fluxHotel1,fluxHotel2,fluxHotel3);
	    System.out.println(fluxHotels);
	    
	    return fluxHotels;
	   }

	public Mono<HotelDto> buscarHotelById(String id) {
		WebClient webclient = WebClient.create("http://localhost:8080/hotels/" + id);
		
		return webclient.get().retrieve().bodyToMono(HotelDto.class);	
	}
	
	public Mono<HotelDto> updateHotel(HotelDto hotel)
    {
		WebClient webclient = WebClient.create("http://localhost:8080/hotels/1");
		
		Mono<HotelDto> hotelData = webclient.get().retrieve().bodyToMono(HotelDto.class);
		
		
		/*CompraDto existingCompra = hotelData.get(compra.getAggregateId());
        if(existingCompra!=null)
        {
        	existingCompra.setActiu(true);
        }*/
        return null ;   ////Mono.just(existingCompra);
    }
}