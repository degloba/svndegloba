package com.degloba.ecommerce.sales.application.api.service;

import com.google.appengine.api.datastore.Key;

import com.degloba.ecommerce.sales.offer.domain.Offer;

@SuppressWarnings("serial")
public class OfferChangedExcpetion extends RuntimeException {
	
	private Key orderId;
	private Offer seenOffer;
	private Offer newOffer;
	
	public OfferChangedExcpetion(Key orderId, Offer seenOffer,
			Offer newOffer) {
		this.orderId = orderId;
		this.seenOffer = seenOffer;
		this.newOffer = newOffer;
	}
	
	public Key getOrderId() {
		return orderId;
	}
	
	public Offer getSeenOffer() {
		return seenOffer;
	}
	
	public Offer getNewOffer() {
		return newOffer;
	}

}
