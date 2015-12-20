package com.degloba.ecommerce.shipping.listeners;

import javax.inject.Inject;

// Ecommerce
import com.degloba.ecommerce.canonicalmodel.events.OrderSubmittedEvent;
import com.degloba.ecommerce.sales.readmodel.orders.OrderDto;
import com.degloba.ecommerce.sales.readmodel.orders.OrderFinder;
import com.degloba.ecommerce.shipping.domain.Shipment;
import com.degloba.ecommerce.shipping.domain.ShipmentFactory;
import com.degloba.ecommerce.shipping.domain.IShipmentRepository;

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
    private OrderFinder orderFinder;

    @Inject
    private IShipmentRepository repository;

    @EventListener(asynchronous = true)
    public void handle(OrderSubmittedEvent event) {
        OrderDto orderDetails = orderFinder.find(event.getOrderId());
        Shipment shipment = factory.createShipment(orderDetails.getOrderId());
        repository.save(shipment);
    }
}
