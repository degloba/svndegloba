package com.degloba.ecommerce.crm.application.commands;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

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
