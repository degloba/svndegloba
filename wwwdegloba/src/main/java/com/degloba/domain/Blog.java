package com.degloba.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.appengine.api.datastore.Key;


@Entity
public class Blog {

   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   private Key id;

   private String titol;
   private String descripcio;

	public Key getId() {
		return id;
	}

	public void setId(Key id) {	
		this.id = id;
	}

	public String getTitol() {
		return titol;
	}

	public void setTitol(String titol) {
		this.titol = titol;
	}

	public String getDescripcio() {
		return descripcio;
	}

	public void setDescripcio(String descripcio) {
		this.descripcio = descripcio;
	}
   
}