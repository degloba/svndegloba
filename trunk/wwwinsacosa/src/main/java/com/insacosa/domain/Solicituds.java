package com.insacosa.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.datanucleus.api.jpa.annotations.Extension;

import com.google.appengine.api.datastore.Key;

import ddd.domain.BaseAggregateRoot;
import ddd.domain.annotations.DomainAggregateRoot;


@Entity
@DomainAggregateRoot
public class Solicituds extends BaseAggregateRoot{
	
	@Id    
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Extension(vendorName="datanucleus", key="gae.encoded-pk",value="true")
	private String id;
	
	private String solicitudKey;
	
	
	// problema a GAE
	//http://thoughts.inphina.com/2010/08/07/managing-multiple-parent-persistence-problem-in-app-engine/
	
	@ManyToOne(fetch = FetchType.EAGER)
    private Inmobles inmoble;

	@Transient
    private Usuaris usuari;
	
	// Unowned relationship
	private String usuariKey;
	

	
	 // Accessors for the fields. JPA doesn't use these, but your application does.    
	public String getSolicitudKey() {        
		return solicitudKey;    
		}
	
	public Solicituds() {
	}


	public Inmobles getInmoble() {
		return inmoble;
	}

	public void setInmoble(Inmobles inmoble) {
		this.inmoble = inmoble;
	}

	public Usuaris getUsuari() {
		return usuari;
	}

	public void setUsuari(Usuaris usuari) {
		this.usuari = usuari;
	}

	public String getUsuariKey() {
		return usuariKey;
	}

	public void setUsuariKey(String usuariKey) {
		this.usuariKey = usuariKey;
	}

	public void setSolicitudKey(String solicitudKey) {
		this.solicitudKey = solicitudKey;
	}



}
