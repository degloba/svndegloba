package com.degloba.ecommerce.vendes.application.events;

public class CreditPurchaseEvent extends PurchaseEvent {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String creditCardNumber;
    private String item;

    public CreditPurchaseEvent(long amount, String item, String creditCardNumber) {
        super(amount);
        this.item = item;
        this.creditCardNumber = creditCardNumber;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public String getItem() {
        return item;
    }

    @Override
    public String toString() {
        return "CreditPurchaseEvent{" +
                "creditCardNumber='" + creditCardNumber + '\'' +
                ", item='" + item + '\'' +
                "} " + super.toString();
    }
    
}