package com.degloba.ecommerce.vendes.productes.domain.persistence.rdbms.jpa;

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
//import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.AggregateId;
import com.degloba.persistence.domain.sharedkernel.Money;
import com.degloba.persistence.rdbms.api.jpa.AggregateId;
import com.degloba.persistence.rdbms.api.jpa.BaseAggregateRoot;
import com.degloba.persistence.rdbms.api.jpa.BaseEntity;

/**
 * @category Entitat de persistència que defineix un producte
 * 
 * @author degloba
 *
 */
@Entity
@AggregateRoot
public class Producte extends BaseAggregateRoot{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	@Embedded
	private Money preu;
	
	private String nom;
	
	@Enumerated(EnumType.STRING)
	private TipusProducte tipusProducte;
	
	private Producte(){}
	
	Producte(AggregateId aggregateId, Money preu, String nom, TipusProducte tipusProducte){
		this.aggregateId = aggregateId;
		this.preu = preu;
		this.nom = nom;
		this.tipusProducte = tipusProducte;
	}
	
	public boolean isAvailabe(){		
		return ! isRemoved();//TODO explore domain rules
	}
	
	public Money getPreu() {
		return preu;
	}
	
	public String getNom() {
		return nom;
	}
	
	public TipusProducte getProducteType() {
		return tipusProducte;
	}
	
	public ProducteData generateSnapshot(){
		return new ProducteData(getAggregateId(), preu, nom, tipusProducte, new Date());
	}
	
}
