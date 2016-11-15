package com.degloba.ecommerce.sales.reservation.domain.persistence.rdbms.jpa;

import com.degloba.domain.annotations.ValueObject;
import com.degloba.domain.persistence.rdbms.jpa.canonicalmodel.publishedlanguage.AggregateId;
import com.degloba.domain.sharedkernel.Money;


@ValueObject
public class ReservedProduct {

	private String name;
	
	private Money totalCost;
	
	private AggregateId productId;

	private int quantity;
	
	public ReservedProduct(AggregateId productId, String name, int quantity, Money totalCost) {
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
	
	public AggregateId getProductId() {
		return productId;
	}

	public int getQuantity() {
		return quantity;
	}
}
