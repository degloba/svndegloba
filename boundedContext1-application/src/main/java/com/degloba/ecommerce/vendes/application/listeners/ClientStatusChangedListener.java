package com.degloba.ecommerce.vendes.application.listeners;

import javax.inject.Inject;

import com.degloba.cqrs.query.PaginatedResult;

import com.degloba.ecommerce.crm.domain.events.CustomerStatusChangedEvent;
import com.degloba.ecommerce.vendes.application.services.DescompteService;
import com.degloba.ecommerce.vendes.cqrs.readmodel.finders.IVendaFinder;
import com.degloba.ecommerce.vendes.ordres.cqrs.readmodel.ComandesQuery;
import com.degloba.ecommerce.vendes.ordres.cqrs.readmodel.dtos.ComandaDto;
import com.degloba.persistence.domain.AggregateId;
import com.degloba.persistence.domain.sharedkernel.Money;


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
public class ClientStatusChangedListener {

	@Inject
	private DescompteService descompteService;
	
	@Inject
	private IVendaFinder salesFinder;
	
	@EventListener
	public void handle(CustomerStatusChangedEvent event){
		// recuperem la consulta de l'ordre a partir de l'id del client
		ComandesQuery comandesQuery = new ComandesQuery(null, event.getCustomerId());
		
		PaginatedResult<ComandaDto> orders = salesFinder.query(comandesQuery);
		
		Money discount = calculateDiscout(event.getCustomerId());
		
		for(ComandaDto dto : orders.getItems()){
			descompteService.applyDiscount(dto.getComandaId(), discount);
		}
	}

	private Money calculateDiscout(AggregateId customerId) {
		// TODO explore domain rules
		return new Money(10);
	}
}
