package com.degloba.ecommerce.sales.application.api.service;


import com.degloba.ecommerce.sales.application.api.command.OrderDetailsCommand;
import com.degloba.ecommerce.sales.offer.domain.persistence.rdbms.jpa.Offer;

// Domain


/*
 *  degloba
 *  
 *  RDBMS/JPA
 */
public interface IOrderingService {
	// 1.
	public long createOrder();

	// 2.
	public void addProduct(long orderId, long productId, int quantity);
	
	// 3. 
	public Offer calculateOffer(long orderId);

	// 4.
	public void confirm(long orderId, OrderDetailsCommand orderDetailsCommand, Offer seenOffer)
			throws OfferChangedException;
}
