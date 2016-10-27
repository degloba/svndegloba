package com.degloba.ecommerce.sales.reservation.domain.factories;

import java.util.Date;
import java.util.UUID;

import javax.inject.Inject;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;

import com.degloba.domain.annotations.DomainFactory;
//import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.AggregateId;
import com.degloba.ecommerce.sales.client.domain.persistence.rdbms.jpa.Client;
import com.degloba.ecommerce.sales.reservation.domain.persistence.rdbms.jpa.Reservation;
import com.degloba.ecommerce.sales.reservation.domain.persistence.rdbms.jpa.Reservation.ReservationStatus;

import com.degloba.domain.sharedkernel.exceptions.DomainOperationException;
/*import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;*/

@DomainFactory
public class ReservationFactory {

	@Inject
	private AutowireCapableBeanFactory spring;
	
	public Reservation create(Client client){
		if (! canReserve(client))
			throw new DomainOperationException(client.getAggregateId(), "Client can not create reservations");
		
		/////Key aggregateId = KeyFactory.stringToKey( UUID.randomUUID().toString());
		Reservation reservation = new Reservation(1, ReservationStatus.OPENED, client.generateSnapshot(), new Date());
		spring.autowireBean(reservation);
		
		addGratis(reservation, client);
		
		return reservation;
	}

	private void addGratis(Reservation reservation, Client client) {				
		//TODO explore domain rules
	}

	private boolean canReserve(Client client) {
		return true;//TODO explore domain rules (ex: cleint's debts, stataus etc) 
	}

}
