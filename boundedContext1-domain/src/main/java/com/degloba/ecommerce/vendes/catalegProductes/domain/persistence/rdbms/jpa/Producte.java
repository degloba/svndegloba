package com.degloba.ecommerce.vendes.catalegProductes.domain.persistence.rdbms.jpa;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.degloba.domain.annotations.AggregateRoot;
import com.degloba.persistence.rdbms.jpa.AggregateId;
import com.degloba.persistence.rdbms.jpa.BaseAggregateRoot;
import com.degloba.persistence.rdbms.jpa.BaseEntity;
import com.degloba.persistence.rdbms.jpa.BaseEntity;

//import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.AggregateId;
import com.degloba.persistence.domain.sharedkernel.Money;


@Entity
@AggregateRoot
public class Producte extends BaseAggregateRoot{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	@Embedded
	private Money preu;
	
	private String name;
	
	@Enumerated(EnumType.STRING)
	private TipusProducte tipusProducte;
	
	private Producte(){}
	
	Producte(AggregateId aggregateId, Money preu, String name, TipusProducte tipusProducte){
		this.aggregateId = aggregateId;
		this.preu = preu;
		this.name = name;
		this.tipusProducte = tipusProducte;
	}
	
	public boolean isAvailabe(){		
		return ! isRemoved();//TODO explore domain rules
	}
	
	public Money getPreu() {
		return preu;
	}
	
	public String getName() {
		return name;
	}
	
	public TipusProducte getProductType() {
		return tipusProducte;
	}
	
	public ProducteData generateSnapshot(){
		return new ProducteData(getAggregateId(), preu, name, tipusProducte, new Date());
	}
	
}
