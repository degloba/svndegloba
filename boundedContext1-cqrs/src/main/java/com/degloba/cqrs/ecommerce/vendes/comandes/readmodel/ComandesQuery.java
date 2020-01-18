package com.degloba.ecommerce.vendes.comandes.cqrs.readmodel;

import com.degloba.persistence.rdbms.jpa.AggregateId;

/**
 * @author degloba
 * 
 * @category Consulta : Order (patró CQRS)
 *
 */
public class ComandesQuery {

	private String producteNom;
	
	public ComandesQuery(String producteNom, AggregateId clientId){
		this.producteNom = producteNom;
		//TODO search by client
	}
	
	public String getProducteNom() {
		return producteNom;
	}
}
