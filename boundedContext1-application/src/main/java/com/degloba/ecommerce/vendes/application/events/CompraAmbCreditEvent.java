package com.degloba.ecommerce.vendes.application.events;

/**
 * @category s'ha produit una compra amb targeta de crèdit
 * 
 * @author degloba
 *
 */
public class CompraAmbCreditEvent extends CompraEvent {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String creditCardNumber;
    private String item;

    public CompraAmbCreditEvent(long quantitat, String item, String creditCardNumber) {
        super(quantitat);
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