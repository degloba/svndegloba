package com.degloba.ecommerce.vendes.comandes.cqrs.queries;

import com.degloba.persistence.rdbms.api.jpa.AggregateId;

import lombok.Value;

/**
 * @author degloba
 * 
 * @category Consulta : Order (patró CQRS)
 *
 */
@Value
public class ComandesQuery {

	private String producteNom;
	
	public ComandesQuery(String producteNom, AggregateId clientId){
		this.producteNom = producteNom;
		//TODO search by client
	}


}
