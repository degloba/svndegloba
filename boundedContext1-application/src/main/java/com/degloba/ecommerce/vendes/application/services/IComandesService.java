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
public interface IComandesService {
	// 1.
	public AggregateId createOrder();

	// 2.
	public void addProduct(AggregateId comandaId, AggregateId productId, int quantity);
	
	// 3. 
	public Oferta calculateOffer(AggregateId comandaId);

	// 4.
	public void confirm(AggregateId comandaId, OrderDetailsCommand orderDetailsCommand, Oferta seenOffer)
			throws OfferChangedException;
}
