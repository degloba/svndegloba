package com.insacosa.entitats;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.datanucleus.jpa.annotations.Extension;

import com.google.appengine.api.datastore.Key;

@Entity
public class Fotos {

	@Id    
	@GeneratedValue(strategy = GenerationType.IDENTITY)  
	@Extension(vendorName="datanucleus", key="gae.encoded-pk",value="true")
	private String id;
	
	private Key key;
	
	private Inmobles inmobles;
	private byte[] imatge;
	private String descripcio;

	public Fotos() {
	}

	public Fotos(Key key) {
		this.key = key;
	}

	
	public Fotos(Key key, Inmobles inmobles, byte[] imatge, String descripcio) {
		this.key = key;
		this.inmobles = inmobles;
		this.imatge = imatge;
		this.descripcio = descripcio;
	}

	 // Accessors for the fields. JPA doesn't use these, but your application does.    
	public Key getKey() {        
		return key;    
		}
	
	public void setKey(Key key) {
		this.key = key;
	}
		

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDINMOBLE")
	public Inmobles getInmobles() {
		return this.inmobles;
	}

	public void setInmobles(Inmobles inmobles) {
		this.inmobles = inmobles;
	}

	@Column(name = "IMATGE")
	public byte[] getImatge() {
		return this.imatge;
	}

	public void setImatge(byte[] imatge) {
		this.imatge = imatge;
	}

	@Column(name = "DESCRIPCIO", length = 50)
	public String getDescripcio() {
		return this.descripcio;
	}

	public void setDescripcio(String descripcio) {
		this.descripcio = descripcio;
	}

}
