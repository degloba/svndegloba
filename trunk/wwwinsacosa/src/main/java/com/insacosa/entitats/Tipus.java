package com.insacosa.entitats;

import com.google.appengine.api.datastore.Key;
import com.insacosa.interfaces.Objecte;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Tipus extends Objecte  {

	@Id    
	@GeneratedValue(strategy = GenerationType.IDENTITY)  
	private Key key;
	
	private String nom;
	private Set<Inmobles> inmobleses = new HashSet<Inmobles>(0);
	private Set<Caracteristiques> caracteristiqueses = new HashSet<Caracteristiques>(
			0);

	public Tipus() {
	}

	public Tipus(Key key) {
		this.key = key;
	}

	public void setKey(Key key) {
		this.key = key;
	}
	
	public Tipus(Key key, String nom, Set<Inmobles> inmobleses,
			Set<Caracteristiques> caracteristiqueses) {
		this.key = key;
		this.nom = nom;
		this.inmobleses = inmobleses;
		this.caracteristiqueses = caracteristiqueses;
	}

    // Accessors for the fields. JPA doesn't use these, but your application does.    
    public Key getKey() {        
    	return key;    
    	}

	@Column(name = "NOM", length = 50)
	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tipus")
	public Set<Inmobles> getInmobleses() {
		return this.inmobleses;
	}

	public void setInmobleses(Set<Inmobles> inmobleses) {
		this.inmobleses = inmobleses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tipus")
	public Set<Caracteristiques> getCaracteristiqueses() {
		return this.caracteristiqueses;
	}

	public void setCaracteristiqueses(Set<Caracteristiques> caracteristiqueses) {
		this.caracteristiqueses = caracteristiqueses;
	}

}
