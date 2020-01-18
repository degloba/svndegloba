package com.degloba.ecommerce.crm.cqrs.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * @category Command que definix que s'ha fet una comanda<br>
 * Implementat amb Axon
 * 
 * @author degloba
 *
 */
public class FerComandaCommand {

	@TargetAggregateIdentifier
    private final String comandaId;
    private final String producte;
    
        // constructor, getters, equals/hashCode and toString
    
	public FerComandaCommand(String comandaId, String producte) {
		super();
		this.comandaId = comandaId;
		this.producte = producte;
	}
     
    public String getComandaId() {
		return comandaId;
	}
	

	public String getProducte() {
		return producte;
	}
      
    
}
