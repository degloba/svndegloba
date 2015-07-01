package com.degloba.ecommerce.sales.readmodel.orders;

import com.google.appengine.api.datastore.Key;

//import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.AggregateId;

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
