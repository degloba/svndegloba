package com.degloba.ecommerce.sales.cqrs.readmodel.orders;

import com.google.appengine.api.datastore.Key;

public class OrderQuery {

	private String productName;
	
	public OrderQuery(String productName, Key clientId){
		this.productName = productName;
		//TODO search by client
	}
	
	public String getProductName() {
		return productName;
	}
}
