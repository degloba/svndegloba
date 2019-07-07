package com.degloba.ecommerce.vendes.eventsourcing.events;

import com.degloba.ecommerce.vendes.eventsourcing.events.CompraEvent;

public class CompraEnEfectiuEvent extends CompraEvent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String item;

    public CompraEnEfectiuEvent(long quantitat, String item) {
        super(quantitat);
        this.item = item;
    }

    public String getItem() {
        return item;
    }

    @Override
    public String toString() {
        return "CashPurchaseEvent{" +
                "item='" + item + '\'' +
                "} " + super.toString();
    }
}
