package com.degloba.ecommerce.application.events.eventbus.impl.guava.eventsubscribers.vendes;

import java.util.ArrayList;
import java.util.List;

import com.degloba.ecommerce.vendes.eventsourcing.events.CompraAmbCreditEvent;
import com.degloba.ecommerce.vendes.eventsourcing.events.CompraEnEfectiuEvent;
import com.degloba.ecommerce.vendes.eventsourcing.events.SimpleEvent;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

/**
 * @category subscriptor que està subscrit a diferents tipus d'events a la vegada.<br>
 * Implementació amb Google
 * 
 * @author degloba
 *
 */
public class MultiHandlerSubscriber {

    private List<CompraEnEfectiuEvent> cashEvents = new ArrayList<CompraEnEfectiuEvent>();
    private List<CompraAmbCreditEvent> creditEvents = new ArrayList<CompraAmbCreditEvent>();
    private List<SimpleEvent> simpleEvents = new ArrayList<SimpleEvent>();

    private MultiHandlerSubscriber() {
    }

    @Subscribe
    public void handleCashEvents(CompraEnEfectiuEvent event) {
        cashEvents.add(event);
    }

    @Subscribe
    public void handleCreditEvents(CompraAmbCreditEvent event) {
        creditEvents.add(event);
    }

    @Subscribe
    public void handleSimpleEvents(SimpleEvent event) {
        simpleEvents.add(event);
    }

    public List<CompraEnEfectiuEvent> getCashEvents() {
        return cashEvents;
    }

    public List<CompraAmbCreditEvent> getCreditEvents() {
        return creditEvents;
    }

    public List<SimpleEvent> getSimpleEvents() {
        return simpleEvents;
    }

    public static MultiHandlerSubscriber instance(EventBus eventBus) {
        MultiHandlerSubscriber subscriber = new MultiHandlerSubscriber();
        eventBus.register(subscriber);
        return subscriber;
    }
}
