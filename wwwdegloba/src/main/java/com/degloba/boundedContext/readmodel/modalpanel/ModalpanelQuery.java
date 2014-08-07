package com.degloba.boundedContext.readmodel.modalpanel;

import domain.canonicalmodel.publishedlanguage.AggregateId;

public class ModalpanelQuery<K> {
		
	private String productName;
	
	public ModalpanelQuery(String productName, K clientId){
		this.productName = productName;
		//TODO search by client
	}
	
	public String getProductName() {
		return productName;
	}
	
}