package com.degloba.boundedContext.casino.application.api.service;

import com.degloba.boundedContext.modalpanel.domain.Modalpanel;




public interface ICasinoApp<K> {
	
	public K createModalpanel();

	
	
	public void addModalpanel(Modalpanel value);
	
	public void addModalpanelById(K value);

	
/*	// 3. 
	public Offer calculateOffer(AggregateId orderId);

	// 4.
	public void confirm(AggregateId orderId, OrderDetailsCommand orderDetailsCommand, Offer seenOffer)
			throws OfferChangedExcpetion;*/

}
