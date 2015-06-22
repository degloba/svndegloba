/*
 * Copyright 2011-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.degloba.ecommerce.sales.payment;

import javax.inject.Inject;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Transient;

//import com.degloba.annotations.AggregateRoot;

import com.degloba.domain.BaseAggregateRoot;

import com.degloba.organisation.canonicalmodel.events.PaymentRolledBackEvent;
//import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.AggregateId;
import com.degloba.domain.canonicalmodel.publishedlanguage.ClientData;

import com.degloba.domain.sharedkernel.Money;
import com.google.appengine.api.datastore.Key;

/**
 * 
 * @author Slawek
 *
 */
//@AggregateRoot
@Entity
public class Payment extends BaseAggregateRoot{

	@Embedded
	private ClientData clientData;
	
	@Embedded
	private Money amount;
	
	@Transient
	@Inject
	private PaymentFactory paymentFactory;
	
	@SuppressWarnings("unused")
	private Payment(){}
	
	Payment(Key aggregateId, ClientData clientData, Money amount) {
		///////this.aggregateId = aggregateId;
		this.clientData = clientData;
		this.amount = amount;
	}

	public Payment rollBack(){
		//TODO explore domain rules
		eventPublisher.publish(new PaymentRolledBackEvent(getAggregateId()));
		return paymentFactory.createPayment(clientData, amount.multiplyBy(-1));
	}
}
