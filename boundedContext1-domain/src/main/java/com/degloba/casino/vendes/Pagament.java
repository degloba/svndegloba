package com.degloba.casino.vendes;

// JPA

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;



import com.degloba.annotations.AggregateRoot;
// Aggregate
import com.degloba.domain.BaseAggregateRoot;
import com.degloba.domain.canonicalmodel.publishedlanguage.AggregateId;


@AggregateRoot
@Entity
public class Pagament extends BaseAggregateRoot{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String descripcio;
	private String titol;
	
	public Pagament() {}
	
	/*@Embedded
	private ClientData clientData; */
	
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
	

	public void setAggregateId(AggregateId generate) {
		// TODO Auto-generated method stub
		
	}

	
	
	
}
