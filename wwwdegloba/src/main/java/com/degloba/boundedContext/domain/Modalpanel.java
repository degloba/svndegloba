package com.degloba.boundedContext.domain;

// JPA
import javax.persistence.Entity;

// Aggregate
import domain.BaseAggregateRoot;
import domain.annotations.AggregateRoot;
import domain.canonicalmodel.publishedlanguage.AggregateId;

import domain.canonicalmodel.publishedlanguage.ClientData;

@AggregateRoot
@Entity
public class Modalpanel extends BaseAggregateRoot{
	
	private Integer modalpanelid;
	private String descripcio;
	private String titol;
	
	/*@Embedded
	private ClientData clientData; */
	
	public Integer getModalpanelid() {
		return modalpanelid;
	}
	public void setModalpanelid(Integer modalpanelid) {
		this.modalpanelid = modalpanelid;
	}
	public String getDescripcio() {
		return descripcio;
	}
	public void setDescripcio(String descripcio) {
		this.descripcio = descripcio;
	}
	public String getTitol() {
		return titol;
	}
	public void setTitol(String titol) {
		this.titol = titol;
	}
	
	
	public Modalpanel() {}
	
	/*	public Modalpanel(AggregateId aggregateId) {
		this.aggregateId = aggregateId;
	}*/
	
	
	
}
