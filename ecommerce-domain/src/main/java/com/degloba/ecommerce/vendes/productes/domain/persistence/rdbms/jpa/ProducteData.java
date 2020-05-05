package com.degloba.ecommerce.vendes.productes.domain.persistence.rdbms.jpa;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.degloba.domain.annotations.ValueObject;
import com.degloba.persistence.domain.sharedkernel.Money;
import com.degloba.persistence.rdbms.api.jpa.AggregateId;
import com.degloba.persistence.rdbms.api.jpa.BaseEntity;


@Embeddable
@ValueObject
public class ProducteData {

	@Embedded
	private AggregateId producteId;
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "denomination", column = @Column(name = "productPrice_denomination")),
		@AttributeOverride(name = "currencyCode", column = @Column(name = "productPrice_currencyCode")) })
	private Money preu;
	
	private String nom;
	
	@Temporal(TemporalType.DATE)
	private Date snapshotDate;
	
	@Enumerated(EnumType.STRING)
	private TipusProducte type;

	
	private ProducteData(){}
	
	ProducteData(AggregateId producteId, Money preu, String nom, TipusProducte type, 
			Date snapshotDate) {
		this.producteId = producteId;
		this.preu = preu;
		this.nom = nom;
		this.snapshotDate = snapshotDate;
		this.type = type;
	}

	public AggregateId getProducteId() {
		return producteId;
	}

	public Money getPreu() {
		return preu;
	}

	public String getNom() {
		return nom;
	}

	public Date getSnapshotDate() {
		return snapshotDate;
	}
	
	public TipusProducte getType() {
		return type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((preu == null) ? 0 : preu.hashCode());
		////////////result = prime * result
				///////////+ ((producteId == null) ? 0 : producteId.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProducteData other = (ProducteData) obj;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (preu == null) {
			if (other.preu != null)
				return false;
		} else if (!preu.equals(other.preu))
			return false;
		/*if (producteId == null) {
			if (other.producteId != null)
				return false;
		} else if (!producteId.equals(other.producteId))
			return false;*/
		if (type != other.type)
			return false;
		return true;
	}
	
	
}
