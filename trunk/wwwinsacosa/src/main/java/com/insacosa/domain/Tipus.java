package com.insacosa.domain;

import com.google.appengine.api.datastore.Key;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.datanucleus.api.jpa.annotations.Extension;

@Entity
public class Tipus   {

	@Id    
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Extension(vendorName="datanucleus", key="gae.encoded-pk",value="true") 
	private Key key;
	
	
	private String nom;
	//private Set<Inmobles> inmobleses = new HashSet<Inmobles>(0);
	//private Set<Caracteristiques> caracteristiqueses = new HashSet<Caracteristiques>(
	//		0);

	
	@Transient
	private Set<Inmobles> inmobleses;
	
	@Basic(fetch = FetchType.EAGER)
    private Set<String> inmoblesesKeys = new HashSet<String>();
	
	@Transient
	private Set<Caracteristiques> caracteristiqueses;
		
	@Basic(fetch = FetchType.EAGER)
    private Set<String> caracteristiquesesKeys = new HashSet<String>();

	

	// Unowned relationship
    private String inmobleKey;


	public Tipus() {
	}

	public Tipus(String nom) {
		this.nom = nom;
	}

	
	@Column(name = "NOM", length = 50)
	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	//@OneToMany(fetch = FetchType.LAZY, mappedBy = "tipus")
	public Set<Inmobles> getInmobleses() {
		return this.inmobleses;
	}

	public void setInmobleses(Set<Inmobles> inmobleses) {
		this.inmobleses = inmobleses;
	}

	public Set<String> getCaracteristiquesesKeys() {
		return caracteristiquesesKeys;
	}

	public void setCaracteristiquesesKeys(Set<String> caracteristiquesesKeys) {
		this.caracteristiquesesKeys = caracteristiquesesKeys;
	}

	public String getInmobleKey() {
		return inmobleKey;
	}

	public void setInmobleKey(String inmobleKey) {
		this.inmobleKey = inmobleKey;
	}

	public Set<String> getInmoblesesKeys() {
		return inmoblesesKeys;
	}

	public void setInmoblesesKeys(Set<String> inmoblesesKeys) {
		this.inmoblesesKeys = inmoblesesKeys;
	}

	public Set<Caracteristiques> getCaracteristiqueses() {
		return caracteristiqueses;
	}

	public void setCaracteristiqueses(Set<Caracteristiques> caracteristiqueses) {
		this.caracteristiqueses = caracteristiqueses;
	}

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	


}
