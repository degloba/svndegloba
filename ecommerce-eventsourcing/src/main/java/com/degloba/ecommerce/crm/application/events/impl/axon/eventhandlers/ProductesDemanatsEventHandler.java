package com.degloba.ecommerce.crm.application.eventhandlers.impl.axon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;

import com.degloba.ecommerce.crm.facade.dtos.ProducteDemanatDto;
import com.degloba.ecommerce.crm.facade.dtos.TrobarTotsProductesDemanatsDto;
import com.degloba.ecommerce.eventsourcing.events.ComandaFetaEvent;

/**
 * 
 * @author degloba
 *
 * @category Implementat en Axon
 */
public class ProductesDemanatsEventHandler {
	private final Map<String, ProducteDemanatDto> ProductesDemanats = new HashMap<>();
	 
    @EventHandler
    public void on(ComandaFetaEvent event) {
        String orderId = event.getComandaId();
        ProductesDemanats.put(orderId, new ProducteDemanatDto(orderId, event.getProducte()));
    }
 
    // Event Handlers for OrderConfirmedEvent and OrderShippedEvent...
    
    
    @QueryHandler
    public List<ProducteDemanatDto> handle(TrobarTotsProductesDemanatsDto query) {
        return new ArrayList<>(ProductesDemanats.values());
    }
    
}
