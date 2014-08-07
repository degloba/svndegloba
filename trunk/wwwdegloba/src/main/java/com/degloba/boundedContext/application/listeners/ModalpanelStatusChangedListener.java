package com.degloba.boundedContext.application.listeners;

import infrastructure.events.annotations.EventListener;
import infrastructure.events.annotations.EventListeners;

import javax.inject.Inject;

import query.PaginatedResult;

import com.degloba.boundedContext.readmodel.modalpanel.IModalpanelFinder;
import com.degloba.boundedContext.readmodel.modalpanel.ModalpanelDto;
import com.degloba.boundedContext.readmodel.modalpanel.ModalpanelQuery;
import com.degloba.canonicalmodel.events.ModalpanelStatusChangedEvent;

/**
 * Sample Anti-corruption Layer: translates Customer-Client vocabulary
 * <br>
 * Applies discount 
 * 
 * @author degloba
 *
 */
@EventListeners
public class ModalpanelStatusChangedListener {

	/*@Inject
	private DiscountingService discountingService;*/
	@Inject
	private IModalpanelFinder modalpanelFinder; 

	@EventListener
	public void handle(ModalpanelStatusChangedEvent<Long> event){
		ModalpanelQuery<Long> modalpanelQuery = new ModalpanelQuery<Long>(null, event.getModalPanelId());
		PaginatedResult<ModalpanelDto> orders = modalpanelFinder.query(modalpanelQuery);

		/*Money discount = calculateDiscout(event.getCustomerId());

		for(OrderDto dto : orders.getItems()){
			discountingService.applyDiscount(dto.getOrderId(), discount);
		}*/
	}

	/* private Money calculateDiscout(AggregateId customerId) {
		// TODO explore domain rules
		return new Money(10);
	}*/
}