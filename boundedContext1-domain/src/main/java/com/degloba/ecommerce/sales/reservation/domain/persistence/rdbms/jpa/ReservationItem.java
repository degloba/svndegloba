package com.degloba.ecommerce.sales.reservation.domain.persistence.rdbms.jpa;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;


import com.degloba.ecommerce.sales.productscatalog.domain.Product;
import com.degloba.domain.persistence.rdbms.jpa.BaseEntity;
import com.degloba.domain.sharedkernel.exceptions.DomainOperationException;

@Entity
class ReservationItem extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	private Product product;
	
	private int quantity;

	@SuppressWarnings("unused")
	private ReservationItem(){}
	
	ReservationItem(Product product, int quantity) {
		this.product = product;
		this.quantity = quantity;
	}

	void changeQuantityBy(int change) {
		int changed = quantity + change;
		if (changed <= 0)
			throw new DomainOperationException(1, "change below 1");
		this.quantity = changed;
	}
	
	public Product getProduct() {
		return product;
	}

	public int getQuantity() {
		return quantity;
	}

	
}
