package com.degloba.ecommerce.sales.cqrs.readmodel.orders;


public class OrderQuery {

	private String productName;
	
	public OrderQuery(String productName, long clientId){
		this.productName = productName;
		//TODO search by client
	}
	
	public String getProductName() {
		return productName;
	}
}
