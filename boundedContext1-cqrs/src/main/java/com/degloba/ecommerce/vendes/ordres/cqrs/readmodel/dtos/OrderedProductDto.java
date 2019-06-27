package com.degloba.ecommerce.vendes.ordres.cqrs.readmodel.dtos;

import com.degloba.persistence.rdbms.jpa.AggregateId;

/** 
 * @category Defineix un producte d'una {@link Commanda}
 * 
 *  * @author degloba
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