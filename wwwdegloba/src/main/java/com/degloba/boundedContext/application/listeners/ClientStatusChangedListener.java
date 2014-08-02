package com.degloba.boundedContext.application.listeners;

import infrastructure.events.annotations.EventListener;

import javax.inject.Inject;

public class ClientStatusChangedListener {

	/*@Inject
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

	private Money calculateDiscout(AggregateId customerId) {
		// TODO explore domain rules
		return new Money(10);
	}*/
}