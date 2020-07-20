package com.degloba.ecommerce.vendes.eventsourcing.events.eventlisteners;

import javax.inject.Inject;

import com.degloba.ecommerce.crm.domain.events.EstatClientCanviatEvent;
import com.degloba.ecommerce.vendes.application.services.DescompteService;
import com.degloba.ecommerce.vendes.comandes.cqrs.queries.ComandaQuery;
import com.degloba.ecommerce.vendes.comandes.cqrs.queries.finders.IVendaFinder;
import com.degloba.ecommerce.vendes.comandes.facade.dtos.ComandaDto;
import com.degloba.events.annotations.EventListenerAnnotation;
import com.degloba.events.annotations.EventListenersAnnotation;
import com.degloba.persistence.domain.sharedkernel.Money;
import com.degloba.persistence.rdbms.api.jpa.AggregateId;


/**
 * Sample Anti-corruption Layer: translates Customer-Client vocabulary
 * <br>
 * Aplica un descompte
 * 
 * @author degloba
 *
 */
@EventListenersAnnotation
public class EstatClientCanviatListener {

	@Inject
	private DescompteService descompteService;
	
	@Inject
	private IVendaFinder vendesFinder;
	
	@EventListenerAnnotation
	public void handle(EstatClientCanviatEvent event){

		ComandaQuery comandaQuery = new ComandaQuery(null,null, null);	
		PaginatedResult<ComandaDto> orders = vendesFinder.query(comandaQuery);
		
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
