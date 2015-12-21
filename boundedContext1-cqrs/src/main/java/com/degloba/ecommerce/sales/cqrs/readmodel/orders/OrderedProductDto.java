package com.degloba.ecommerce.sales.cqrs.readmodel.orders;

import com.google.appengine.api.datastore.Key;


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