package com.degloba.boundedContext.modalpanel.readmodel;


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