package com.degloba.ecommerce.sales.orders.cqrs.readmodel;

import com.degloba.domain.persistence.rdbms.jpa.canonicalmodel.publishedlanguage.AggregateId;

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
