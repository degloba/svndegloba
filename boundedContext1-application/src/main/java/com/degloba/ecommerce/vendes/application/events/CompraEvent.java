package com.degloba.ecommerce.vendes.application.events;

import com.degloba.domain.event.DomainEvent;

public abstract class CompraEvent extends DomainEvent{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected long amount;

    public CompraEvent(long amount) {
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