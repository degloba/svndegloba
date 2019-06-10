package com.degloba.ecommerce.sales.application.exceptions;


import com.degloba.ecommerce.vendes.ofertes.domain.persistence.rdbms.jpa.Oferta;
import com.degloba.persistence.domain.AggregateId;

@SuppressWarnings("serial")
public class OfferChangedException extends RuntimeException {
	
	private AggregateId orderId;
	private Oferta seenOffer;
	private Oferta newOffer;
	
	public OfferChangedException(AggregateId orderId, Oferta seenOffer,
			Oferta newOffer) {
		this.orderId = orderId;
		this.seenOffer = seenOffer;
		this.newOffer = newOffer;
	}
	
	public AggregateId getOrderId() {
		return orderId;
	}
	
	public Oferta getSeenOffer() {
		return seenOffer;
	}
	
	public Oferta getNewOffer() {
		return newOffer;
	}

}
