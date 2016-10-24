package com.degloba.ecommerce.sales.offer.domain.factories;

import com.degloba.domain.annotations.DomainFactory;
import com.degloba.ecommerce.sales.client.domain.persistence.rdbms.jpa.Client;
import com.degloba.ecommerce.sales.offer.discounts.QuantityDiscount;
import com.degloba.ecommerce.sales.offer.domain.policies.DiscountPolicy;

@DomainFactory
public class DiscountFactory {

	public DiscountPolicy create(Client client) {
		// TODO explore domain rules
		return new QuantityDiscount(20, 3);//20% for over 3 items
	}

}
