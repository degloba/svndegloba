package com.degloba.ecommerce.sales.readmodel.orders;

import com.google.appengine.api.datastore.Key;

//import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.AggregateId;

// TODO more attrs
public class OrderedProductDto {
	private Key offerId;

	public Key getOfferId() {
		return offerId;
	}

	public void setOfferId(Key offerId) {
		this.offerId = offerId;
	}
}