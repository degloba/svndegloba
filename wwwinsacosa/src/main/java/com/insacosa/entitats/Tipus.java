package com.insacosa.entitats;

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

import org.datanucleus.api.jpa.annotations.Extension;

@Entity
public class Tipus extends Objecte  {

	@Id    
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Extension(vendorName="datanucleus", key="gae.encoded-pk",value="true") 
	private String id;
	
	private String tipusKey;
	
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

	public Tipus(String tipusKey) {
		this.tipusKey = tipusKey;
	}

	public void setTipusKey(String tipusKey) {
		this.tipusKey = tipusKey;
	}
	
	public Tipus(String tipusKey, String nom, Set<Inmobles> inmobleses,
			Set<String> caracteristiquesesKeys) {
		this.tipusKey = tipusKey;
		this.nom = nom;
		this.inmobleses = inmobleses;
		this.caracteristiquesesKeys = caracteristiquesesKeys;
	}

    // Accessors for the fields. JPA doesn't use these, but your application does.    
    public String getTipusKey() {        
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

	public Set<Caracteristiques> getCaracteristiqueses() {
		return caracteristiqueses;
	}

	public void setCaracteristiqueses(Set<Caracteristiques> caracteristiqueses) {
		this.caracteristiqueses = caracteristiqueses;
	}

	


}
