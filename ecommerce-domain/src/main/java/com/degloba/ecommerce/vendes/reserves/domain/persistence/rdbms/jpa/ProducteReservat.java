package com.degloba.ecommerce.vendes.reserves.domain.persistence.rdbms.jpa;

import com.degloba.domain.annotations.ValueObject;
import com.degloba.persistence.domain.sharedkernel.Money;
import com.degloba.persistence.rdbms.api.jpa.AggregateId;
import com.degloba.persistence.rdbms.api.jpa.BaseEntity;

/**
 * @category
 * 
 * @author degloba
 *
 */
@ValueObject
public class ProducteReservat {

	private String name;
	
	private Money totalCost;
	
	private AggregateId producteId;

	private int quantitat;
	
	public ProducteReservat(AggregateId producteId, String name, int quantitat, Money totalCost) {
		this.producteId = producteId;
		this.name = name;
		this.quantitat = quantitat;
		this.totalCost = totalCost;
	}

	public String getName() {
		return name;
	}
	
	public Money getTotalCost() {
		return totalCost;
	}
	
	public AggregateId getProducteId() {
		return producteId;
	}

	public int getQuantitat() {
		return quantitat;
	}
}
