package com.degloba.ecommerce.vendes.client.domain.persistence.rdbms.jpa;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.degloba.domain.annotations.AggregateRoot;
import com.degloba.ecommerce.vendes.pagaments.domain.factories.PaymentFactory;
import com.degloba.ecommerce.vendes.pagaments.domain.persistence.rdbms.jpa.Payment;
import com.degloba.persistence.domain.ClientData;
import com.degloba.persistence.domain.sharedkernel.Money;
import com.degloba.persistence.rdbms.jpa.BaseAggregateRoot;


@Entity
@AggregateRoot
public class Client extends BaseAggregateRoot{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private String name;
	
	
	@Inject
	@Transient
	private PaymentFactory paymentFactory;
	
	public ClientData generateSnapshot(){		
		return new ClientData(aggregateId, name);
	}

	public boolean canAfford(Money amount) {		
		return true;//TODO explore domain rules ex: credit limit
	}

	/**
	 * Sample model: one aggregate creates another.<br>
	 * Client model does not compose Payment - therefore it does not manage Payment lifecycle.<br>
	 * Application layer is responsible for managing Payment lifecycle;
	 * 
	 * @param amount
	 * @return
	 */
	public Payment charge(Money amount) {
		if (! canAfford(amount)){			
			domainError("Can not afford: " + amount);
		}
		// TODO facade to the payment module
		
		return paymentFactory.createPayment(generateSnapshot(), amount);
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


}
