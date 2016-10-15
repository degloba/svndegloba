package com.degloba.ecommerce.shipping.listeners;

import javax.inject.Inject;

// Ecommerce
import com.degloba.ecommerce.canonicalmodel.events.OrderSubmittedEvent;
import com.degloba.ecommerce.shipping.domain.Shipment;
import com.degloba.ecommerce.shipping.domain.ShipmentFactory;
import com.degloba.ecommerce.shipping.domain.persistence.rdbms.jpa.IShippingRepository;
import com.degloba.ecommerce.sales.cqrs.readmodel.ISalesFinder;
// CQRS (ecommerce)
import com.degloba.ecommerce.sales.cqrs.readmodel.orders.OrderDto;


// Event
import com.degloba.event.annotations.EventListeners;
import com.degloba.event.annotations.EventListener;


/**
 * When an order is submitted by a customer automatically create a shipment in
 * WAITING status.
 * 
 * NOTICE: This is an example of communication across multiple bounded contexts
 * using events. In this context we can't access Order aggregate directly so we
 * use DTO from the read model instead.
 * 
 */
@EventListeners
public class OrderSubmittedForShippingListener {

    @Inject
    private ShipmentFactory factory;

    @Inject
    private ISalesFinder salesFinder;

    @Inject
    private IShippingRepository shippingRepository;

    @EventListener(asynchronous = true)
    public void handle(OrderSubmittedEvent event) {
        OrderDto orderDetails = salesFinder.find(event.getOrderId());
        Shipment shipment = factory.createShipment(orderDetails.getOrderId());
        shippingRepository.save(shipment);
    }
}
