package com.degloba.ecommerce.sales.application.events.guava.eventbus.events;

import com.degloba.domain.event.ADomainEvent;

public abstract class PurchaseEvent extends ADomainEvent{
    protected long amount;

    public PurchaseEvent(long amount) {
        this.amount = amount;
    }

    public long getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "PurchaseEvent{" +
                "amount=" + amount +
                '}';
    }
}