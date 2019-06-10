package com.degloba.ecommerce.sales.application.listeners;

import javax.inject.Inject;

import com.degloba.cqrs.query.PaginatedResult;

import com.degloba.ecommerce.crm.domain.events.CustomerStatusChangedEvent;
import com.degloba.ecommerce.sales.application.services.DiscountingService;
import com.degloba.ecommerce.vendes.cqrs.readmodel.finders.IVendaFinder;
import com.degloba.ecommerce.vendes.ordres.cqrs.readmodel.OrdreQuery;
import com.degloba.ecommerce.vendes.ordres.cqrs.readmodel.dtos.OrderDto;
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
	private DiscountingService discountingService;
	
	@Inject
	private IVendaFinder salesFinder;
	
	@EventListener
	public void handle(CustomerStatusChangedEvent event){
		// recuperem la consulta de l'ordre a partir de l'id del client
		OrdreQuery ordreQuery = new OrdreQuery(null, event.getCustomerId());
		
		PaginatedResult<OrderDto> orders = salesFinder.query(ordreQuery);
		
		Money discount = calculateDiscout(event.getCustomerId());
		
		for(OrderDto dto : orders.getItems()){
			discountingService.applyDiscount(dto.getOrderId(), discount);
		}
	}

	private Money calculateDiscout(AggregateId customerId) {
		// TODO explore domain rules
		return new Money(10);
	}
}
