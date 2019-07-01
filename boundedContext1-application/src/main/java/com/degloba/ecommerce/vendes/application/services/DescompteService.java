package com.degloba.ecommerce.vendes.application.services;

import com.degloba.domain.annotations.InternalApplicationService;

import com.degloba.persistence.domain.sharedkernel.Money;
import com.degloba.persistence.rdbms.jpa.AggregateId;

/**
 * @author degloba
 * 
 * @category Servei de descompte
 *
 */
@InternalApplicationService
public class DescompteService {

	public void aplicaDescompte(AggregateId comandaId, Money quantitat){
		//TODO implement
	}
}
