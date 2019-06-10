package com.degloba.ecommerce.vendes.reserves.domain.factories;

import java.util.Date;

import javax.inject.Inject;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;

import com.degloba.domain.annotations.DomainFactory;
import com.degloba.ecommerce.vendes.client.domain.persistence.rdbms.jpa.Client;
import com.degloba.ecommerce.vendes.reserves.domain.persistence.rdbms.jpa.Reservation;
import com.degloba.ecommerce.vendes.reserves.domain.persistence.rdbms.jpa.Reservation.ReservationStatus;
import com.degloba.persistence.domain.AggregateId;
import com.degloba.persistence.domain.sharedkernel.exceptions.DomainOperationException;


/**
 * @author degloba
 * 
 * @category Factoria {@link Reservation}
 */
@DomainFactory
public class ReservationFactory {

	@Inject
	private AutowireCapableBeanFactory spring;
	
	public Reservation create(Client client){
		if (! canReserve(client))
			throw new DomainOperationException(client.getAggregateId(), "Client can not create reservations");
		
		Reservation reservation = new Reservation(AggregateId.generate(), ReservationStatus.OPENED, client.generateSnapshot(), new Date());
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
