package com.degloba.lloguers.domain.persistence.nosql.impl.googleDatastore.api.objectify;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

/**
 * @category Entitat (Objectify) : Adreça 
 */
@Entity
public class Address {
	
	@Id Long id;
	String province;
    String city;
    String detail;
    
    
	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}

    

}
