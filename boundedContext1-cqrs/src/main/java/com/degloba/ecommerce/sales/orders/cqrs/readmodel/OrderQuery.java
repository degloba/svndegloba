package com.degloba.ecommerce.sales.orders.cqrs.readmodel;

import com.degloba.persistence.domain.AggregateId;


public class OrderQuery {

	private String productName;
	
	public OrderQuery(String productName, AggregateId clientId){
		this.productName = productName;
		//TODO search by client
	}
	
	public String getProductName() {
		return productName;
	}
}
