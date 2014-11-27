package com.degloba.boundedContext.application.api.service;


public interface ModalpanelService<K> {
	
	public K createModalpanel();

	// 2.
	/*public void addModalpanel(AggregateId modalpanelId, AggregateId productId, int quantity);*/
	public void addModalpanel(K modalpanelId);

	
/*	// 3. 
	public Offer calculateOffer(AggregateId orderId);

	// 4.
	public void confirm(AggregateId orderId, OrderDetailsCommand orderDetailsCommand, Offer seenOffer)
			throws OfferChangedExcpetion;*/

}
