package com.insacosa.entitats;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.appengine.api.datastore.Key;


@Entity
public class Caractinmobles {

	@Id    
	@GeneratedValue(strategy = GenerationType.IDENTITY)    
	private Key key;

	public Caractinmobles() {
	}

	 // Accessors for the fields. JPA doesn't use these, but your application does.    
	public Key getKey() {        
		return key;    
		}


}
