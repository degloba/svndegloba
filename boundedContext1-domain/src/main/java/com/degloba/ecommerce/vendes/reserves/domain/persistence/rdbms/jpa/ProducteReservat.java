package com.degloba.ecommerce.vendes.reserves.domain.persistence.rdbms.jpa;

import com.degloba.domain.annotations.ValueObject;
import com.degloba.persistence.rdbms.jpa.BaseEntity;
import com.degloba.persistence.domain.AggregateId;
import com.degloba.persistence.domain.sharedkernel.Money;


@ValueObject
public class ProducteReservat {

	private String name;
	
	private Money totalCost;
	
	private AggregateId productId;

	private int quantity;
	
	public ProducteReservat(AggregateId productId, String name, int quantity, Money totalCost) {
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
