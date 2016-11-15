package com.degloba.ecommerce.sales.application.services;

import com.degloba.domain.annotations.InternalApplicationService;
import com.degloba.domain.persistence.rdbms.jpa.canonicalmodel.publishedlanguage.AggregateId;
// Domain
import com.degloba.domain.sharedkernel.Money;


@InternalApplicationService
public class DiscountingService {

	public void applyDiscount(AggregateId orderId, Money amount){
		//TODO implement
	}
}
