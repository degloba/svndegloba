package com.degloba.ecommerce.sales.application.api.service;

import com.google.appengine.api.datastore.Key;

import com.degloba.ecommerce.sales.application.api.command.OrderDetailsCommand;

// Domain
import com.degloba.ecommerce.sales.offer.domain.Offer;


public interface OrderingService {
	// 1.
	public Key createOrder();

	// 2.
	public void addProduct(Key orderId, Key productId, int quantity);
	
	// 3. 
	public Offer calculateOffer(Key orderId);

	// 4.
	public void confirm(Key orderId, OrderDetailsCommand orderDetailsCommand, Offer seenOffer)
			throws OfferChangedExcpetion;
}
