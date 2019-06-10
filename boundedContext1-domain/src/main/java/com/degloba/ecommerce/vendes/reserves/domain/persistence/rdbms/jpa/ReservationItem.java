package com.degloba.ecommerce.vendes.reserves.domain.persistence.rdbms.jpa;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import com.degloba.ecommerce.vendes.catalegProductes.domain.persistence.rdbms.jpa.Producte;
import com.degloba.persistence.domain.AggregateId;
import com.degloba.persistence.domain.sharedkernel.exceptions.DomainOperationException;
import com.degloba.persistence.rdbms.jpa.BaseEntity;

/*
 * Un Item d'una Reserva
 */
@Entity
class ReservationItem extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	
	@ManyToOne
	private Producte producte;
	
	private int quantity;

	private ReservationItem(){}
	
	ReservationItem(Producte producte, int quantity) {
		this.producte = producte;
		this.quantity = quantity;
	}

	void changeQuantityBy(int change) {
		int changed = quantity + change;
		if (changed <= 0)
			throw new DomainOperationException(AggregateId.generate(), "change below 1");
		this.quantity = changed;
	}
	
	public Producte getProduct() {
		return producte;
	}

	public int getQuantity() {
		return quantity;
	}

	@Override
	public boolean existed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean notExisted() {
		// TODO Auto-generated method stub
		return false;
	}

	
}
