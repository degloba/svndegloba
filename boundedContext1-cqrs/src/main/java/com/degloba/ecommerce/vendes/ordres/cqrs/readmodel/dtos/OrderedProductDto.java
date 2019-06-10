package com.degloba.ecommerce.sales.orders.cqrs.readmodel.dtos;

import com.degloba.persistence.domain.AggregateId;
import com.degloba.persistence.rdbms.jpa.BaseEntity;

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