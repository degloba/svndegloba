package com.degloba.boundedContext.application.api.service;

import domain.canonicalmodel.publishedlanguage.AggregateId;

public interface ModalpanelService {
	
	public AggregateId createModalpanel();

	// 2.
	/*public void addModalpanel(AggregateId modalpanelId, AggregateId productId, int quantity);*/
	public void addModalpanel(AggregateId modalpanelId);
	
/*	// 3. 
	public Offer calculateOffer(AggregateId orderId);

	// 4.
	public void confirm(AggregateId orderId, OrderDetailsCommand orderDetailsCommand, Offer seenOffer)
			throws OfferChangedExcpetion;*/

}
