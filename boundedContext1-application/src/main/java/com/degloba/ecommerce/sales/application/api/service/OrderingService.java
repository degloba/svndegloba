package com.degloba.ecommerce.sales.application.api.service;


import com.degloba.ecommerce.sales.application.api.command.OrderDetailsCommand;

// Domain
import com.degloba.ecommerce.sales.offer.domain.Offer;

/*
 *  degloba
 *  
 *  RDBMS/JPA
 */
public interface OrderingService {
	// 1.
	public long createOrder();

	// 2.
	public void addProduct(long orderId, long productId, int quantity);
	
	// 3. 
	public Offer calculateOffer(long orderId);

	// 4.
	public void confirm(long orderId, OrderDetailsCommand orderDetailsCommand, Offer seenOffer)
			throws OfferChangedExcpetion;
}
