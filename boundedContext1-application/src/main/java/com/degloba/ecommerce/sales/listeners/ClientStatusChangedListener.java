package com.degloba.ecommerce.sales.listeners;

import javax.inject.Inject;

import query.PaginatedResult;

import com.degloba.annotations.event.EventListener;
import com.degloba.annotations.event.EventListeners;
import com.degloba.ecommerce.canonicalmodel.events.CustomerStatusChangedEvent;
//import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.AggregateId;
import com.degloba.ecommerce.sales.internal.discounts.DiscountingService;
import com.degloba.ecommerce.sales.readmodel.orders.OrderDto;
import com.degloba.ecommerce.sales.readmodel.orders.OrderFinder;
import com.degloba.ecommerce.sales.readmodel.orders.OrderQuery;
import com.degloba.domain.sharedkernel.Money;
import com.google.appengine.api.datastore.Key;

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

	private Money calculateDiscout(Key customerId) {
		// TODO explore domain rules
		return new Money(10);
	}
}
