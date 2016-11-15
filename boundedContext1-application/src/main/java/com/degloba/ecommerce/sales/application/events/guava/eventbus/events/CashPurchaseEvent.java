package com.degloba.ecommerce.sales.application.events.guava.eventbus.events;

public class CashPurchaseEvent extends PurchaseEvent {
	private String item;

    public CashPurchaseEvent(long amount, String item) {
        super(amount);
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
