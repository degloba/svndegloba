package com.degloba.boundedContext.clients.domain;

import javax.inject.Inject;
import javax.persistence.Entity;
import javax.persistence.Transient;






import com.degloba.boundedContext.vendes.domain.Pagament;
import com.degloba.boundedContext.vendes.domain.PagamentFactory;
import com.degloba.sharedkernel.Money;

import domain.support.BaseAggregateRoot;
import domain.annotations.AggregateRoot;
import domain.canonicalmodel.publishedlanguage.ClientData;


@Entity
@AggregateRoot
public class Client extends BaseAggregateRoot{

	private String name;
	
	@Inject
	@Transient
	private PagamentFactory paymentFactory;
	
	public ClientData generateSnapshot(){
		//return new ClientData(this.getAggregateId(), name);
		return null;
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
