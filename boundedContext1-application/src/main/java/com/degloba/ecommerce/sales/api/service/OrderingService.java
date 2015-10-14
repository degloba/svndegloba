package com.degloba.ecommerce.sales.api.service;

//import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.AggregateId;
import com.google.appengine.api.datastore.Key;

import com.degloba.ecommerce.sales.api.command.OrderDetailsCommand;
import com.degloba.ecommerce.sales.offer.Offer;


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
