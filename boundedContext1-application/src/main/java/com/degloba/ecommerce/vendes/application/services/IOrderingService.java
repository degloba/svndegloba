package com.degloba.ecommerce.vendes.application.services;



import com.degloba.ecommerce.vendes.application.commands.OrderDetailsCommand;
import com.degloba.ecommerce.vendes.application.exceptions.OfferChangedException;
import com.degloba.ecommerce.vendes.ofertes.domain.persistence.rdbms.jpa.Oferta;
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
	public Oferta calculateOffer(AggregateId orderId);

	// 4.
	public void confirm(AggregateId orderId, OrderDetailsCommand orderDetailsCommand, Oferta seenOffer)
			throws OfferChangedException;
}
