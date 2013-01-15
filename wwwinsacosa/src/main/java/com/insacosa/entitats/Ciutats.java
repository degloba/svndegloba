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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.datanucleus.jpa.annotations.Extension;

@Entity
public class Ciutats extends Objecte {

	@Id    
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Extension(vendorName="datanucleus", key="gae.encoded-pk",value="true") 
	private Key key;
	
	private String ciutatKey;
	
	private String code;
	private String name;
	
    @ManyToOne(fetch = FetchType.LAZY)
	private Provincies keyProv;

	
	@Transient
	private Set<Inmobles> inmobleses;
	
	@Basic(fetch = FetchType.EAGER)
    private Set<String> inmoblesesKeys = new HashSet<String>();

	
	public Ciutats() {
	}

	public Ciutats(String ciutatKey, String code, String name, Provincies keyProv) {
		this.ciutatKey = ciutatKey;
		this.code = code;
		this.name = name;
		this.keyProv = keyProv;
	}

	public Ciutats(String ciutatKey, String code, String name, Provincies keyProv,
			Set<Inmobles> inmobleses) {
		this.ciutatKey = ciutatKey;
		this.code = code;
		this.name = name;
		this.keyProv = keyProv;
		this.inmobleses = inmobleses;
	}

	 // Accessors for the fields. JPA doesn't use these, but your application does.    
	public String getCiutatKey() {        
		return ciutatKey;    
		}
	
	public void setCiutatKey(String ciutatKey) {
		this.ciutatKey = ciutatKey;
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

	@Column(name = "keyProv", nullable = false)
	public Provincies getKeyProv() {
		return this.keyProv;
	}

	public void setKeyProv(Provincies keyProv) {
		this.keyProv = keyProv;
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

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}






}
