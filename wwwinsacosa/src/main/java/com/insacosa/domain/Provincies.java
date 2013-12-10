package com.insacosa.domain;


import com.google.appengine.api.datastore.Key;

import domain.BaseAggregateRoot;
import domain.annotations.DomainAggregateRoot;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
@DomainAggregateRoot
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Provincies extends BaseAggregateRoot{

	
	@Id    
	@GeneratedValue(strategy = GenerationType.IDENTITY)  
	private Key key;
	
	private String provinciaKey;
	
	private String code;
	private String name;
	
	@Transient
	private Set<Inmobles> inmobleses;
	
	@Basic(fetch = FetchType.EAGER)
    private Set<String> inmoblesesKeys = new HashSet<String>();

	@OneToMany(mappedBy = "keyProv", cascade = CascadeType.ALL)
    private List<Ciutats> ciutats = new ArrayList<Ciutats>();
	
	

	public Provincies() {
	}

	public Provincies(String provinciaKey, String code, String name) {
		this.provinciaKey = provinciaKey;
		this.code = code;
		this.name = name;
	}

	public Provincies(String provinciaKey, String code, String name,
			Set<Inmobles> inmobleses) {
		this.provinciaKey = provinciaKey;
		this.code = code;
		this.name = name;
		//this.inmobleses = inmobleses;
	}

    // Accessors for the fields. JPA doesn't use these, but your application does.    
    public String getProvinciaKey() {        
    	return provinciaKey;    
    	}
    
	public void setProvinciaKey(String provinciaKey) {
		this.provinciaKey = provinciaKey;
	}

	@Column(name = "code", nullable = false, length = 16)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "name", nullable = false, length = 30)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Inmobles> getInmobleses() {
		return inmobleses;
	}

	public void setInmobleses(Set<Inmobles> inmobleses) {
		this.inmobleses = inmobleses;
	}

	public Set<String> getInmoblesesKeys() {
		return inmoblesesKeys;
	}

	public void setInmoblesesKeys(Set<String> inmoblesesKeys) {
		this.inmoblesesKeys = inmoblesesKeys;
	}

	public List<Ciutats> getCiutats() {
		return ciutats;
	}

	public void setCiutats(List<Ciutats> ciutats) {
		this.ciutats = ciutats;
	}

/*	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}*/




		
	
	
/*
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "provincies")
	public Set<Inmobles> getInmobleses() {
		return this.inmobleses;
	}

	public void setInmobleses(Set<Inmobles> inmobleses) {
		this.inmobleses = inmobleses;
	}
*/
}
