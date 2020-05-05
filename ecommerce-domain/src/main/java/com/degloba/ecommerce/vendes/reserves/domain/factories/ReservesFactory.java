package com.degloba.ecommerce.vendes.reserves.domain.factories;

import java.util.Date;

import javax.inject.Inject;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;

import com.degloba.domain.annotations.DomainFactory;
import com.degloba.ecommerce.vendes.domain.persistence.rdbms.jpa.client.Client;
import com.degloba.ecommerce.vendes.reserves.domain.persistence.rdbms.jpa.Reserva;
import com.degloba.ecommerce.vendes.reserves.domain.persistence.rdbms.jpa.Reserva.EstatReserva;
import com.degloba.persistence.rdbms.api.jpa.AggregateId;
import com.degloba.persistence.rdbms.api.jpa.exceptions.DomainOperationException;


/**
 * @author degloba
 * 
 * @category fàbrica de {@link Reserva}s
 */
@DomainFactory
public class ReservesFactory {

	@Inject
	private AutowireCapableBeanFactory spring;
	
	public Reserva crea(Client client){
		if (! potReservar(client))
			throw new DomainOperationException(client.getAggregateId(), "Client can not create reservations");
		
		Reserva reserva = new Reserva(AggregateId.generate(), EstatReserva.OPENED, client.generateSnapshot(), new Date());
		spring.autowireBean(reserva);
		
		addGratis(reserva, client);
		
		return reserva;
	}

	private void addGratis(Reserva reserva, Client client) {				
		//TODO explore domain rules
	}

	private boolean potReservar(Client client) {
		return true;//TODO explore domain rules (ex: cleint's debts, stataus etc) 
	}

}
