package com.degloba.ecommerce.vendes.application.events.guava.eventbus.subscriber;

import java.util.ArrayList;
import java.util.List;

import com.degloba.ecommerce.vendes.cqrs.events.CompraAmbCreditEvent;
import com.degloba.ecommerce.vendes.cqrs.events.CompraEnEfectiuEvent;
import com.degloba.event.bus.google.events.SimpleEvent;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

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
