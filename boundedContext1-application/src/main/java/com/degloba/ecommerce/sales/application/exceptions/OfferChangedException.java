package com.degloba.ecommerce.sales.application.exceptions;


import com.degloba.ecommerce.sales.offer.domain.persistence.rdbms.jpa.Offer;
import com.degloba.persistence.domain.AggregateId;

@SuppressWarnings("serial")
public class OfferChangedException extends RuntimeException {
	
	private AggregateId orderId;
	private Offer seenOffer;
	private Offer newOffer;
	
	public OfferChangedException(AggregateId orderId, Offer seenOffer,
			Offer newOffer) {
		this.orderId = orderId;
		this.seenOffer = seenOffer;
		this.newOffer = newOffer;
	}
	
	public AggregateId getOrderId() {
		return orderId;
	}
	
	public Offer getSeenOffer() {
		return seenOffer;
	}
	
	public Offer getNewOffer() {
		return newOffer;
	}

}
