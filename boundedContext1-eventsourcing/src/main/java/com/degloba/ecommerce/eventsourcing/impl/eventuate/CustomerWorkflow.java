package com.degloba.ecommerce.eventsourcing.impl.eventuate;

/*import java.util.concurrent.CompletableFuture;

import com.google.type.Money;
import com.networknt.eventuate.common.EntityWithIdAndVersion;
import com.networknt.eventuate.common.EventHandlerContext;
import com.networknt.eventuate.common.EventHandlerMethod;
import com.networknt.eventuate.common.EventSubscriber;*/


//@EventSubscriber(id = "customerWorkflow")
public class CustomerWorkflow {

/*  @EventHandlerMethod
  public CompletableFuture<EntityWithIdAndVersion<Customer>> reserveCredit(
          EventHandlerContext<OrderCreatedEvent> ctx) {
    OrderCreatedEvent event = ctx.getEvent();
    Money orderTotal = event.getOrderTotal();
    String customerId = event.getCustomerId();
    String orderId = ctx.getEntityId();

    return ctx.update(Customer.class, customerId, new ReserveCreditCommand(orderTotal, orderId));
  }*/

}