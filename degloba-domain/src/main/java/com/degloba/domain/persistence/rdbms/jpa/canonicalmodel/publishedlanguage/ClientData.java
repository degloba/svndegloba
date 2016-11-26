package com.degloba.domain.persistence.rdbms.jpa.canonicalmodel.publishedlanguage;


import javax.persistence.Embeddable;

import com.degloba.domain.annotations.ValueObject;


/**
 * Client's snapshot
 * 
 * @author degloba
 */
@ValueObject
@Embeddable
public class ClientData {
	
	
	private String name;

	public ClientData(){}
	
	public ClientData( String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

}

