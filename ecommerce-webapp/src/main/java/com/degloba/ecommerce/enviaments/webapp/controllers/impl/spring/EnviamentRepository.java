package com.degloba.ecommerce.enviaments.webapp.controllers.impl.spring;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.degloba.ecommerce.enviaments.facade.dtos.EnviamentDto;
import com.degloba.ecommerce.enviaments.facade.dtos.EstatEnviament;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class EnviamentRepository {
    static Map<Long,EnviamentDto> enviamentData;

    static
    {
    	
		  enviamentData = new HashMap<>(); 
		  enviamentData.put((long) 1,new EnviamentDto(1, 1, EstatEnviament.SENT)); 
		  enviamentData.put((long) 2,new EnviamentDto(1, 2, EstatEnviament.SENT)); 
		  enviamentData.put((long) 3,new EnviamentDto(1, 3, EstatEnviament.SENT)); 
		  enviamentData.put((long) 4,new EnviamentDto(1, 4, EstatEnviament.SENT)); 
		  enviamentData.put((long) 5,new EnviamentDto(1, 5, EstatEnviament.SENT)); 
		  enviamentData.put((long) 6,new EnviamentDto(1, 6, EstatEnviament.SENT)); 
		  enviamentData.put((long) 7,new EnviamentDto(1, 7, EstatEnviament.SENT)); 
		  enviamentData.put((long) 8,new EnviamentDto(1, 8, EstatEnviament.SENT)); 
	 
    }
    
    public Mono<EnviamentDto> findEnviamentById(String id)
    {
        return Mono.just(enviamentData.get(id));
    }
    
    public Flux<EnviamentDto> findAllEnviaments()
    {
        return Flux.fromIterable(enviamentData.values());
    }
    
    public Mono<EnviamentDto> updateEnviament(EnviamentDto enviamentDto)
    {
    	EnviamentDto existingEnviament=enviamentData.get(enviamentDto.getEnviamentId());
        if(existingEnviament!=null)
        {
        	//////existingEnviament.setName(enviamentDto.getName());
        }
        return Mono.just(existingEnviament);
    }
}
