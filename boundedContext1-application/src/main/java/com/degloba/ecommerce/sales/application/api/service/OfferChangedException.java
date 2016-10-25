package com.degloba.ecommerce.sales.application.api.service;

import com.degloba.ecommerce.sales.offer.domain.persistence.rdbms.jpa.Offer;

@SuppressWarnings("serial")
public class OfferChangedException extends RuntimeException {
	
	private long orderId;
	private Offer seenOffer;
	private Offer newOffer;
	
	public OfferChangedException(long orderId, Offer seenOffer,
			Offer newOffer) {
		this.orderId = orderId;
		this.seenOffer = seenOffer;
		this.newOffer = newOffer;
	}
	
	public long getOrderId() {
		return orderId;
	}
	
	public Offer getSeenOffer() {
		return seenOffer;
	}
	
	public Offer getNewOffer() {
		return newOffer;
	}

}
