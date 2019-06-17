package com.degloba.lloguer.domain.persistence.nosql.googleDatastore.api.objectify;


import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import java.io.Serializable;

/**
 * Entitat (Objectify) : Situacio
 */
@Entity
public class Location implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	Long id;
	
	Double latitude;
	Double longitude;
  
	public Location() {
		super();
		// TODO Auto-generated constructor stub
	}

		
	 // getters and setters
	
	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
}
