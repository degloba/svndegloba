package com.degloba.ecommerce.vendes.application.listeners;

import javax.inject.Inject;

import com.degloba.cqrs.query.PaginatedResult;

import com.degloba.ecommerce.crm.domain.events.EstatClientCanviatEvent;
import com.degloba.ecommerce.vendes.application.services.DescompteService;
import com.degloba.ecommerce.vendes.cqrs.readmodel.finders.IVendaFinder;
import com.degloba.ecommerce.vendes.ordres.cqrs.readmodel.ComandesQuery;
import com.degloba.ecommerce.vendes.ordres.cqrs.readmodel.dtos.ComandaDto;

import com.degloba.persistence.domain.sharedkernel.Money;
import com.degloba.persistence.rdbms.jpa.AggregateId;
// Events
import com.degloba.event.annotations.EventListeners;
import com.degloba.event.annotations.EventListener;


/**
 * Sample Anti-corruption Layer: translates Customer-Client vocabulary
 * <br>
 * Aplica un descompte
 * 
 * @author degloba
 *
 */
@EventListeners
public class EstatClientCanviatListener {

	@Inject
	private DescompteService descompteService;
	
	@Inject
	private IVendaFinder vendesFinder;
	
	@EventListener
	public void handle(EstatClientCanviatEvent event){

		ComandesQuery comandesQuery = new ComandesQuery(null, event.getClientId());	
		PaginatedResult<ComandaDto> orders = vendesFinder.query(comandesQuery);
		
		Money discount = calculaDescompte(event.getClientId());
		
		for(ComandaDto dto : orders.getItems()){
			descompteService.aplicaDescompte(dto.getComandaId(), discount);
		}
	}

	private Money calculaDescompte(AggregateId clientId) {
		// TODO explore domain rules
		return new Money(10);
	}
}
