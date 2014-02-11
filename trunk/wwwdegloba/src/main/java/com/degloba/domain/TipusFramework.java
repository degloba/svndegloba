package com.degloba.domain;



import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.google.appengine.api.datastore.Key;


@Entity
public class TipusFramework {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Key id;
	
	private String nom;
	//private Integer idf;
	
	@OneToMany(mappedBy = "tipusframework", cascade = CascadeType.ALL)
    Collection<Framework> frameworks = new ArrayList<Framework>();

	
	
	public Key getId() {
		return id;
	}
	public void setId(Key id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Collection<Framework> getFrameworks() {
		return frameworks;
	}
	public void setFrameworks(Collection<Framework> frameworks) {
		this.frameworks = frameworks;
	}
	
	
}
