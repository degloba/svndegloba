package com.degloba.rent.domain.jpa;

import com.degloba.domain.BaseAggregateRoot;
import com.degloba.domain.BaseEntity;

import javax.persistence.*;

import java.io.Serializable;
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

		
	 // getters and setters
	
	
	
}