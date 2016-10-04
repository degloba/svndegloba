package com.degloba.rent.domain.persistence.nosql.googleDatastore.api.objectify;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class AddressObjectify {
	
	@Id Long id;
	String province;
    String city;
    String detail;
    
    
	public AddressObjectify() {
		super();
		// TODO Auto-generated constructor stub
	}

    

}
