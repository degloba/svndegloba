package com.degloba.ecommerce.vendes.ordres.cqrs.readmodel.dtos;

import com.degloba.persistence.domain.AggregateId;

/**
 * 
 * @author degloba
 *
 * @category Defineix un producte d'una ordre
 */
public class OrderedProductDto {
	private AggregateId offerId;
	
	// TODO more attrs

	public AggregateId getOfferId() {
		return offerId;
	}

	public void setOfferId(AggregateId offerId) {
		this.offerId = offerId;
	}
}