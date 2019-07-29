package com.degloba.ecommerce.crm.application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;


import com.degloba.ecommerce.crm.cqrs.readmodel.ProducteDemanat;
import com.degloba.ecommerce.crm.cqrs.readmodel.TrobarTotsProductesDemanats;
import com.degloba.ecommerce.eventsourcing.events.ComandaFetaEvent;

public class ProductesDemanatsEventHandler {
	private final Map<String, ProducteDemanat> ProductesDemanats = new HashMap<>();
	 
    @EventHandler
    public void on(ComandaFetaEvent event) {
        String orderId = event.getComandaId();
        ProductesDemanats.put(orderId, new ProducteDemanat(orderId, event.getProducte()));
    }
 
    // Event Handlers for OrderConfirmedEvent and OrderShippedEvent...
    
    
    @QueryHandler
    public List<ProducteDemanat> handle(TrobarTotsProductesDemanats query) {
        return new ArrayList<>(ProductesDemanats.values());
    }
    
}
