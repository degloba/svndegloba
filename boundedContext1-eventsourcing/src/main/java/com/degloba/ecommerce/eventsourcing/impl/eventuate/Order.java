package com.degloba.ecommerce.eventsourcing.impl.eventuate;

import java.util.List;


import com.degloba.events.api.IEvent;
import com.networknt.eventuate.common.Event;
import com.networknt.eventuate.common.EventUtil;
import com.networknt.eventuate.common.ReflectiveMutableCommandProcessingAggregate;

public class Order extends ReflectiveMutableCommandProcessingAggregate<Order, OrderCommand> {

	  private OrderState state;
	  private String customerId;

	  public OrderState getState() {
	    return state;
	  }

	  public List<Event> process(CreateOrderCommand cmd) {
	    return EventUtil.events(new OrderCreatedEvent(cmd.getCustomerId(), cmd.getOrderTotal()));
	  }

	  public List<Event> process(ApproveOrderCommand cmd) {
	    return EventUtil.events(new OrderApprovedEvent(customerId));
	  }

	  public List<Event> process(RejectOrderCommand cmd) {
	    return EventUtil.events(new OrderRejectedEvent(customerId));
	  }

	  public void apply(OrderCreatedEvent event) {
	    this.state = OrderState.CREATED;
	    this.customerId = event.getCustomerId();
	  }

	  public void apply(OrderApprovedEvent event) {
	    this.state = OrderState.APPROVED;
	  }


	  public void apply(OrderRejectedEvent event) {
	    this.state = OrderState.REJECTED;
	  }
}
