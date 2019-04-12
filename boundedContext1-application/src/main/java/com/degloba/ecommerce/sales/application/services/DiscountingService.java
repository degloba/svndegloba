package com.degloba.ecommerce.sales.application.services;

import com.degloba.domain.annotations.InternalApplicationService;
import com.degloba.persistence.domain.AggregateId;
import com.degloba.persistence.domain.sharedkernel.Money;


@InternalApplicationService
public class DiscountingService {

	public void applyDiscount(AggregateId orderId, Money amount){
		//TODO implement
	}
}
