package com.degloba.ecommerce.sales.application.services;



import com.degloba.ecommerce.sales.application.commands.OrderDetailsCommand;
import com.degloba.ecommerce.sales.application.exceptions.OfferChangedException;
import com.degloba.ecommerce.sales.offer.domain.persistence.rdbms.jpa.Offer;
import com.degloba.persistence.domain.AggregateId;


/*
 *  Servei d'ordres
 *  
 *  RDBMS/JPA
 */
public interface IOrderingService {
	// 1.
	public AggregateId createOrder();

	// 2.
	public void addProduct(AggregateId orderId, AggregateId productId, int quantity);
	
	// 3. 
	public Offer calculateOffer(AggregateId orderId);

	// 4.
	public void confirm(AggregateId orderId, OrderDetailsCommand orderDetailsCommand, Offer seenOffer)
			throws OfferChangedException;
}
