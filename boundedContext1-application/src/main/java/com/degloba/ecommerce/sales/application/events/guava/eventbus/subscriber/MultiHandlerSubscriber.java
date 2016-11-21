package com.degloba.ecommerce.sales.application.events.guava.eventbus.subscriber;

import java.util.ArrayList;
import java.util.List;

import com.degloba.ecommerce.sales.application.events.CashPurchaseEvent;
import com.degloba.ecommerce.sales.application.events.CreditPurchaseEvent;
import com.degloba.event.guava.eventbus.events.SimpleEvent;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

public class MultiHandlerSubscriber {

    private List<CashPurchaseEvent> cashEvents = new ArrayList<CashPurchaseEvent>();
    private List<CreditPurchaseEvent> creditEvents = new ArrayList<CreditPurchaseEvent>();
    private List<SimpleEvent> simpleEvents = new ArrayList<SimpleEvent>();

    private MultiHandlerSubscriber() {
    }

    @Subscribe
    public void handleCashEvents(CashPurchaseEvent event) {
        cashEvents.add(event);
    }

    @Subscribe
    public void handleCreditEvents(CreditPurchaseEvent event) {
        creditEvents.add(event);
    }

    @Subscribe
    public void handleSimpleEvents(SimpleEvent event) {
        simpleEvents.add(event);
    }

    public List<CashPurchaseEvent> getCashEvents() {
        return cashEvents;
    }

    public List<CreditPurchaseEvent> getCreditEvents() {
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
