package com.insacosa.entitats;

import com.google.appengine.api.datastore.Key;
import com.insacosa.interfaces.Objecte;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;


@Entity
public class Tipus extends Objecte  {

	@Id    
	@GeneratedValue(strategy = GenerationType.IDENTITY)  
	private Key tipusKey;
	
	private String nom;
	//private Set<Inmobles> inmobleses = new HashSet<Inmobles>(0);
	//private Set<Caracteristiques> caracteristiqueses = new HashSet<Caracteristiques>(
	//		0);
	
	
	@Transient
	private Set<Inmobles> inmobleses;
	
	@Basic(fetch = FetchType.EAGER)
    private Set<String> inmoblesesKeys = new HashSet<String>();
	
	@Transient
	private Set<Inmobles> caracteristiqueses;
		
	@Basic(fetch = FetchType.EAGER)
    private Set<String> caracteristiquesesKeys = new HashSet<String>();

	

	// Unowned relationship
    private String inmobleKey;


	public Tipus() {
	}

	public Tipus(Key tipusKey) {
		this.tipusKey = tipusKey;
	}

	public void setTipusKey(Key tipusKey) {
		this.tipusKey = tipusKey;
	}
	
	public Tipus(Key tipusKey, String nom, Set<Inmobles> inmobleses,
			Set<String> caracteristiquesesKeys) {
		this.tipusKey = tipusKey;
		this.nom = nom;
		this.inmobleses = inmobleses;
		this.caracteristiquesesKeys = caracteristiquesesKeys;
	}

    // Accessors for the fields. JPA doesn't use these, but your application does.    
    public Key getTipusKey() {        
    	return tipusKey;    
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

	public Set<Inmobles> getCaracteristiqueses() {
		return caracteristiqueses;
	}

	public void setCaracteristiqueses(Set<Inmobles> caracteristiqueses) {
		this.caracteristiqueses = caracteristiqueses;
	}

	


}
