package com.degloba.ecommerce.sales.internal.discounts;

import com.degloba.domain.annotations.InternalApplicationService;

// Domain
import com.degloba.domain.sharedkernel.Money;

import com.google.appengine.api.datastore.Key;

@InternalApplicationService
public class DiscountingService {

	public void applyDiscount(long orderId, Money amount){
		//TODO implement
	}
}
