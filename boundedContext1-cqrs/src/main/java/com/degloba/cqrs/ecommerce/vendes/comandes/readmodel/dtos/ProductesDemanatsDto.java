package com.degloba.ecommerce.vendes.comandes.cqrs.readmodel.dtos;

import com.degloba.persistence.rdbms.jpa.AggregateId;

/** 
 * @category Defineix un producte d'una {@link Commanda}
 * 
 *  * @author degloba
 */
public class ProductesDemanatsDto {
	private AggregateId offerId;
	
	// TODO more attrs

	public AggregateId getOfferId() {
		return offerId;
	}

	public void setOfferId(AggregateId offerId) {
		this.offerId = offerId;
	}
}