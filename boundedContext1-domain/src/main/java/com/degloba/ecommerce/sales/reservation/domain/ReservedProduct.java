package com.degloba.ecommerce.sales.reservation.domain;

import com.degloba.annotations.ValueObject;
//import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.AggregateId;
import com.degloba.domain.sharedkernel.Money;
import com.google.appengine.api.datastore.Key;

@ValueObject
public class ReservedProduct {

	private String name;
	
	private Money totalCost;
	
	private Key productId;

	private int quantity;
	
	public ReservedProduct(Key productId, String name, int quantity, Money totalCost) {
		this.productId = productId;
		this.name = name;
		this.quantity = quantity;
		this.totalCost = totalCost;
	}

	public String getName() {
		return name;
	}
	
	public Money getTotalCost() {
		return totalCost;
	}
	
	public Key getProductId() {
		return productId;
	}

	public int getQuantity() {
		return quantity;
	}
}
