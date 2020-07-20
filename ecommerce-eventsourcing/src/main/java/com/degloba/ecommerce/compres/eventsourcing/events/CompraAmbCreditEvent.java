package com.degloba.ecommerce.compres.eventsourcing.events;

import lombok.Getter;
import lombok.ToString;

/**
 * @category Event produit en una compra amb targeta de crèdit
 * 
 * @author degloba
 *
 */
@ToString
public class CompraAmbCreditEvent extends CompraEvent {

	private static final long serialVersionUID = 1L;
	
	@Getter private String numeroTargetaCredit;
	@Getter private String item;

    public CompraAmbCreditEvent(long quantitat, String item, String numeroTargetaCredit) {
        super(quantitat);
        this.item = item;
        this.numeroTargetaCredit = numeroTargetaCredit;
    }
 
}