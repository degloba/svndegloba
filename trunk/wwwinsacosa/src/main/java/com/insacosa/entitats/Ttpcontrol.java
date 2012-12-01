package com.insacosa.entitats;


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


import com.google.appengine.api.datastore.Key;


@Entity
public class Ttpcontrol {

	@Id    
	@GeneratedValue(strategy = GenerationType.IDENTITY)  
	private Key key;
	
	private String tipus;
	
	private Set<Caracteristiques> caracteristiqueses = new HashSet<Caracteristiques>(
			0);

	public Ttpcontrol() {
	}

	public Ttpcontrol(Key key) {
		this.key = key;
	}

	public Ttpcontrol(Key ktpcontrol,
			Set<Caracteristiques> caracteristiqueses) {
		this.key = key;
		this.caracteristiqueses = caracteristiqueses;
	}

    // Accessors for the fields. JPA doesn't use these, but your application does.    
    public Key getKey() {        
    	return key;    
    	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "ttpcontrol")
	public Set<Caracteristiques> getCaracteristiqueses() {
		return this.caracteristiqueses;
	}

	public void setCaracteristiqueses(Set<Caracteristiques> caracteristiqueses) {
		this.caracteristiqueses = caracteristiqueses;
	}

	public String getTipus() {
		return tipus;
	}

	public void setTipus(String tipus) {
		this.tipus = tipus;
	}
	
	
	

}
