package com.degloba.lloguers.domain.persistence.nosql.impl.googleDatastore.api.objectify;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Parent;

import lombok.Value;

/**
 * Entitat (Objectify) : Producte
 */
@Entity
@Value
public class Producte implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	Long id;
	
	String description;
	Double price;
	SubCategoria subCategoria;
	
	@Parent Key<Propietari> propietari;
		
	///////////////////@Parent Key<Location> location;

	
	List<Key<Foto>> fotos = new ArrayList<Key<Foto>>();

	/*public void setLocation(Key<Location> location) {
		this.location = location;
	}*/

	/*public Key<Location> getLocation() {
		return location;
	}*/

}
