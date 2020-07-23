package com.degloba.ecommerce.eventsourcing.events.impl.google.guava.eventbus.eventsubscribers.vendes;

import java.util.ArrayList;
import java.util.List;

import com.degloba.ecommerce.compres.eventsourcing.events.CompraAmbCreditEvent;
import com.degloba.ecommerce.compres.eventsourcing.events.CompraEnEfectiuEvent;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import lombok.NoArgsConstructor;

/**
 * @category Subscriptor que està subscrit a diferents tipus d'events a la vegada.<br>
 * 
 * @implNote Google
 * 
 * @author degloba
 *
 */
@NoArgsConstructor
public class CompraHandlerSubscriber {

    private List<CompraEnEfectiuEvent> compraEnEfectiuEvents = new ArrayList<CompraEnEfectiuEvent>();
    private List<CompraAmbCreditEvent> compraAmbCreditEvents = new ArrayList<CompraAmbCreditEvent>();


    @Subscribe
    public void handleCompraEnEfectiuEvents(CompraEnEfectiuEvent event) {
    	compraEnEfectiuEvents.add(event);
    }

    @Subscribe
    public void handleCompraAmbCreditEvents(CompraAmbCreditEvent event) {
    	compraAmbCreditEvents.add(event);
    }

    public List<CompraEnEfectiuEvent> getCashEvents() {
        return compraEnEfectiuEvents;
    }

    public List<CompraAmbCreditEvent> getCreditEvents() {
        return compraAmbCreditEvents;
    }


    public static CompraHandlerSubscriber instance(EventBus eventBus) {
        CompraHandlerSubscriber subscriber = new CompraHandlerSubscriber();
        eventBus.register(subscriber);
        return subscriber;
    }
}
