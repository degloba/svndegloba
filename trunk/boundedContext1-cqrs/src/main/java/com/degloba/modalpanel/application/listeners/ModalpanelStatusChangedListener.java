package com.degloba.modalpanel.application.listeners;

import com.degloba.infrastructure.events.annotations.EventListener;
import com.degloba.infrastructure.events.annotations.EventListeners;
import com.degloba.modalpanel.readmodel.IModalpanelFinder;
import com.degloba.modalpanel.readmodel.ModalpanelDto;
import com.degloba.modalpanel.readmodel.ModalpanelQuery;

import javax.inject.Inject;

import query.PaginatedResult;


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

	/*@EventListener
	public void handle(ModalpanelStatusChangedEvent<Long> event){
		ModalpanelQuery<Long> modalpanelQuery = new ModalpanelQuery<Long>(null, event.getModalPanelId());
		PaginatedResult<ModalpanelDto> orders = modalpanelFinder.query(modalpanelQuery);

		Money discount = calculateDiscout(event.getCustomerId());

		for(OrderDto dto : orders.getItems()){
			discountingService.applyDiscount(dto.getOrderId(), discount);
		}
	}*/

	/* private Money calculateDiscout(AggregateId customerId) {
		// TODO explore domain rules
		return new Money(10);
	}*/
}