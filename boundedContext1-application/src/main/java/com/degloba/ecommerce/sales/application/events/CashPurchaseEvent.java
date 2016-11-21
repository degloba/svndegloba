package com.degloba.ecommerce.sales.application.events;

public class CashPurchaseEvent extends PurchaseEvent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
