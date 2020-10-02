package com.degloba.ecommerce.enviaments.webapp.services;

import com.degloba.ecommerce.enviaments.domain.persistence.nosql.mongo.Enviament;
import com.degloba.ecommerce.enviaments.facade.dtos.EnviamentDto;

import reactor.core.publisher.Mono;

public class UserrService {
	
	
	public static Mono<EnviamentDto> covertUserDAOToBUserBO(Enviament enviament){
	    return Mono.just(new EnviamentDto(enviament.getEnviamentId(), enviament.getComandaId(), 
	    		enviament.getEstatEnviament()));
	}
	

}
