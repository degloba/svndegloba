package com.degloba.ecommerce.sales.listeners;

import javax.inject.Inject;

// CQRS
import com.degloba.cqrs.query.PaginatedResult;

// Ecommerce
import com.degloba.ecommerce.canonicalmodel.events.CustomerStatusChangedEvent;
import com.degloba.ecommerce.sales.internal.discounts.DiscountingService;

// CQRS (ecommerce)
import com.degloba.ecommerce.sales.cqrs.readmodel.orders.OrderDto;
import com.degloba.ecommerce.sales.cqrs.readmodel.orders.OrderFinder;
import com.degloba.ecommerce.sales.cqrs.readmodel.orders.OrderQuery;

// Domain
import com.degloba.domain.sharedkernel.Money;

//Google app engine
import com.google.appengine.api.datastore.Key;

// Events
import com.degloba.event.annotations.EventListeners;
import com.degloba.event.annotations.EventListener;


/**
 * Sample Anti-corruption Layer: translates Customer-Client vocabulary
 * <br>
 * Applies discount 
 * 
 * @author degloba
 *
 */
@EventListeners
public class ClientStatusChangedListener {

	@Inject
	private DiscountingService discountingService;
	@Inject
	private OrderFinder orderFinder;
	
	@EventListener
	public void handle(CustomerStatusChangedEvent event){
		OrderQuery orderQuery = new OrderQuery(null, event.getCustomerId());
		PaginatedResult<OrderDto> orders = orderFinder.query(orderQuery);
		
		Money discount = calculateDiscout(event.getCustomerId());
		
		for(OrderDto dto : orders.getItems()){
			discountingService.applyDiscount(dto.getOrderId(), discount);
		}
	}

	private Money calculateDiscout(long customerId) {
		// TODO explore domain rules
		return new Money(10);
	}
}
