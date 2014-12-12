package com.degloba.boundedContext.domain.modules.client;

import javax.inject.Inject;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.degloba.boundedContext.domain.modules.ventes.Pagament;
import com.degloba.boundedContext.domain.modules.ventes.PagamentFactory;

import domain.support.BaseAggregateRoot;
import domain.annotations.AggregateRoot;
import domain.canonicalmodel.publishedlanguage.ClientData;

import domain.sharedkernel.Money;

@Entity
@AggregateRoot
public class Client extends BaseAggregateRoot{

	private String name;
	
	@Inject
	@Transient
	private PagamentFactory paymentFactory;
	
	public ClientData generateSnapshot(){
		return new ClientData(this.getAggregateId(), name);
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
	public Pagament charge(Money amount) {
		if (! canAfford(amount)){			
			//domainError("Can not afford: " + amount);
		}
		// TODO facade to the payment module
		
		return paymentFactory.createPagament(generateSnapshot(), amount);
	}
}
