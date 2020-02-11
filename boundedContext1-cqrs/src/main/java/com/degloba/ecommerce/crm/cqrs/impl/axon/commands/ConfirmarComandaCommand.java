package com.degloba.ecommerce.crm.cqrs.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * @category Command que indica<br>
 * Implementat amb Axon
 * 
 * @author degloba
 *
 */
public class ConfirmarComandaCommand {
	
	@TargetAggregateIdentifier
    private final String comandaId;

    // constructor, getters, equals/hashCode and toString
	public ConfirmarComandaCommand(String comandaId) {
		super();
		this.comandaId = comandaId;
	}

	public String getComandaId() {
		return comandaId;
	}
     

	
}
