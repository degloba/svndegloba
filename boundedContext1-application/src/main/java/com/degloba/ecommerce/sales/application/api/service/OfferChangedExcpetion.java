package com.degloba.ecommerce.sales.application.api.service;

import com.google.appengine.api.datastore.Key;

import com.degloba.ecommerce.sales.offer.domain.Offer;

@SuppressWarnings("serial")
public class OfferChangedExcpetion extends RuntimeException {
	
	private long orderId;
	private Offer seenOffer;
	private Offer newOffer;
	
	public OfferChangedExcpetion(long orderId, Offer seenOffer,
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
