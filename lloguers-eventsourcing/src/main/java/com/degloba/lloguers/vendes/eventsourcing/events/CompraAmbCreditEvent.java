package com.degloba.lloguers.vendes.eventsourcing.events;

import java.util.Date;

import com.degloba.lloguers.vendes.eventsourcing.events.CompraEvent;

/**
 * @category s'ha produit una compra amb targeta de crèdit
 * 
 * @author degloba
 *
 */
public class CompraAmbCreditEvent extends CompraEvent {

	private static final long serialVersionUID = 1L;
	private String numeroTargetaCredit;
    private String item;

    public CompraAmbCreditEvent(long quantitat, String item, String numeroTargetaCredit) {
        super(quantitat);
        this.item = item;
        this.numeroTargetaCredit = numeroTargetaCredit;
    }

    public String getNumeroTargetaCredit() {
        return numeroTargetaCredit;
    }

    public String getItem() {
        return item;
    }

    @Override
    public String toString() {
        return "CompraAmbCreditEvent{" +
                "creditCardNumber='" + numeroTargetaCredit + '\'' +
                ", item='" + item + '\'' +
                "} " + super.toString();
    }
    
}