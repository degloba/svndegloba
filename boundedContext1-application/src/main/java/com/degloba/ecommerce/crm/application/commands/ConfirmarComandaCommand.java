package com.degloba.ecommerce.crm.application.commands;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

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
