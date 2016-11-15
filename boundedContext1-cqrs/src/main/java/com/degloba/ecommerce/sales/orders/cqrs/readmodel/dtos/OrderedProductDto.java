package com.degloba.ecommerce.sales.orders.cqrs.readmodel.dtos;

import com.degloba.domain.persistence.rdbms.jpa.canonicalmodel.publishedlanguage.AggregateId;

// TODO more attrs
public class OrderedProductDto {
	private AggregateId offerId;

	public AggregateId getOfferId() {
		return offerId;
	}

	public void setOfferId(AggregateId offerId) {
		this.offerId = offerId;
	}
}