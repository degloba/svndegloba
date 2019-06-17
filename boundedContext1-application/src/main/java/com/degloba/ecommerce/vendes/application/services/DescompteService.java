package com.degloba.ecommerce.vendes.application.services;

import com.degloba.domain.annotations.InternalApplicationService;
import com.degloba.persistence.domain.AggregateId;
import com.degloba.persistence.domain.sharedkernel.Money;

/**
 * @author degloba
 * 
 * @category Servei de descompte
 *
 */
@InternalApplicationService
public class DescompteService {

	public void applyDiscount(AggregateId orderId, Money amount){
		//TODO implement
	}
}
