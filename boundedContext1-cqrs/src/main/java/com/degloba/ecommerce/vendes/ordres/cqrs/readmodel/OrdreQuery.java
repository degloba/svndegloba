package com.degloba.ecommerce.vendes.ordres.cqrs.readmodel;

import com.degloba.persistence.domain.AggregateId;

/**
 * 
 * @author degloba
 * 
 * @category Consulta : Order (patró CQRS)
 * 
 *
 */
public class OrdreQuery {

	private String productName;
	
	public OrdreQuery(String productName, AggregateId clientId){
		this.productName = productName;
		//TODO search by client
	}
	
	public String getProductName() {
		return productName;
	}
}
