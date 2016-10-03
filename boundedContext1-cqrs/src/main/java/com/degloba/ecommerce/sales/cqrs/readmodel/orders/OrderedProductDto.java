package com.degloba.ecommerce.sales.cqrs.readmodel.orders;

// TODO more attrs
public class OrderedProductDto {
	private long offerId;

	public long getOfferId() {
		return offerId;
	}

	public void setOfferId(long offerId) {
		this.offerId = offerId;
	}
}