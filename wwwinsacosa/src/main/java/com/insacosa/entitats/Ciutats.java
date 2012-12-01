package com.insacosa.entitats;

import com.google.appengine.api.datastore.Key;
import com.insacosa.interfaces.Objecte;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Ciutats extends Objecte {

	@Id    
	@GeneratedValue(strategy = GenerationType.IDENTITY)    
	private Key key;
	
	private Date tmstamp;
	private String code;
	private String name;
	private Key keyProv;
	
	private Set<Inmobles> inmobleses = new HashSet<Inmobles>(0);

	public Ciutats() {
	}

	public Ciutats(Key key, Date tmstamp, String code, String name, Key keyProv) {
		this.key = key;
		this.tmstamp = tmstamp;
		this.code = code;
		this.name = name;
		this.keyProv = keyProv;
	}

	public Ciutats(Key key, Date tmstamp, String code, String name, Key keyProv,
			Set<Inmobles> inmobleses) {
		this.key = key;
		this.tmstamp = tmstamp;
		this.code = code;
		this.name = name;
		this.keyProv = keyProv;
		this.inmobleses = inmobleses;
	}

	 // Accessors for the fields. JPA doesn't use these, but your application does.    
	public Key getKey() {        
		return key;    
		}
	
	public void setKey(Key key) {
		this.key = key;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "tmstamp", nullable = false, length = 23)
	public Date getTmstamp() {
		return this.tmstamp;
	}

	public void setTmstamp(Date tmstamp) {
		this.tmstamp = tmstamp;
	}

	@Column(name = "code", nullable = false, length = 16)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "name", nullable = false, length = 40)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "id_prov", nullable = false)
	public Key getKeyProv() {
		return this.keyProv;
	}

	public void setKeyProv(Key keyProv) {
		this.keyProv = keyProv;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "ciutats")
	public Set<Inmobles> getInmobleses() {
		return this.inmobleses;
	}

	public void setInmobleses(Set<Inmobles> inmobleses) {
		this.inmobleses = inmobleses;
	}

}
