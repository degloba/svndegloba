/*
 * Copyright 2011-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.degloba.ecommerce.shipping.listeners;

import javax.inject.Inject;

import com.degloba.annotations.event.EventListener;
import com.degloba.annotations.event.EventListeners;
import com.degloba.ecommerce.canonicalmodel.events.OrderSubmittedEvent;
import com.degloba.ecommerce.sales.readmodel.orders.OrderDto;
import com.degloba.ecommerce.sales.readmodel.orders.OrderFinder;
import com.degloba.ecommerce.shipping.domain.Shipment;
import com.degloba.ecommerce.shipping.domain.ShipmentFactory;
import com.degloba.ecommerce.shipping.domain.ShipmentRepository;

/**
 * When an order is submitted by a customer automatically create a shipment in
 * WAITING status.
 * 
 * NOTICE: This is an example of communication across multiple bounded contexts
 * using events. In this context we can't access Order aggregate directly so we
 * use DTO from the read model instead.
 * 
 * @author Rafał Jamróz
 */
@EventListeners
public class OrderSubmittedForShippingListener {

    @Inject
    private ShipmentFactory factory;

    @Inject
    private OrderFinder orderFinder;

    @Inject
    private ShipmentRepository repository;

    @EventListener(asynchronous = true)
    public void handle(OrderSubmittedEvent event) {
        OrderDto orderDetails = orderFinder.find(event.getOrderId());
        Shipment shipment = factory.createShipment(orderDetails.getOrderId());
        repository.save(shipment);
    }
}
