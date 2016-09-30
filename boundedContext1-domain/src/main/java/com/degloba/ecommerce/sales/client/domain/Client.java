package com.degloba.ecommerce.sales.client.domain;

import javax.inject.Inject;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.degloba.domain.persistence.rdbms.jpa.BaseAggregateRoot;
import com.degloba.domain.persistence.rdbms.jpa.canonicalmodel.publishedlanguage.ClientData;
import com.degloba.ecommerce.sales.payment.domain.Payment;
import com.degloba.ecommerce.sales.payment.domain.PaymentFactory;
import com.degloba.domain.sharedkernel.Money;

@Entity
//@AggregateRoot
public class Client extends BaseAggregateRoot{

	private String name;
	
	@Inject
	@Transient
	private PaymentFactory paymentFactory;
	
	public ClientData generateSnapshot(){
		return null;
		/////////////return new ClientData(Key, name);
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
			//////////domainError("Can not afford: " + amount);
		}
		// TODO facade to the payment module
		
		return paymentFactory.createPayment(generateSnapshot(), amount);
	}
}
