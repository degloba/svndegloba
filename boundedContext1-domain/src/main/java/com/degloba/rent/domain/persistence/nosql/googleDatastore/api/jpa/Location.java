package com.degloba.rent.domain.persistence.nosql.googleDatastore.api.jpa;


import javax.persistence.*;

import com.degloba.domain.persistence.nosql.googleDatastore.api.jpa.BaseAggregateRoot;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.*;

@Entity
public class Location extends BaseAggregateRoot
	implements Serializable
	 {
	
	private Double latitude;
	private Double longitude;
  
	public Location() {
		super();
		// TODO Auto-generated constructor stub
	}

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

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<? extends Annotation> annotationType() {
		// TODO Auto-generated method stub
		return null;
	}

		
	 // getters and setters
	
	
	
}
