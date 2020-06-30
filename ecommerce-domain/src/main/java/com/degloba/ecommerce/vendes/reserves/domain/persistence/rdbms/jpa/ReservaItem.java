package com.degloba.ecommerce.vendes.reserves.domain.persistence.rdbms.jpa;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import com.degloba.ecommerce.vendes.productes.domain.persistence.rdbms.jpa.Producte;
import com.degloba.persistence.rdbms.api.jpa.AggregateId;
import com.degloba.persistence.rdbms.api.jpa.BaseEntity;
import com.degloba.persistence.rdbms.api.jpa.exceptions.DomainOperationException;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @category Un item d'una Reserva
 * 
 * @author degloba
 */
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
class ReservaItem extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	
	@ManyToOne
	private Producte producte;
	
	private int quantitat;

	void changeQuantityBy(int change) {
		int changed = quantitat + change;
		if (changed <= 0)
			throw new DomainOperationException(AggregateId.generate(), "change below 1");
		this.quantitat = changed;
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
