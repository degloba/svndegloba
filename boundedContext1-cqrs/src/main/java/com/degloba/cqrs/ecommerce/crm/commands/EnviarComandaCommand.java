package com.degloba.ecommerce.crm.cqrs.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * @category Command que indica que s'ha d'enviar la comanda<br>
 * Implementat amb Axon
 * 
 * @author degloba
 *
 */
public class EnviarComandaCommand {
	
	@TargetAggregateIdentifier
    private final String comandaId;


	// constructor, getters, equals/hashCode and toString
	public EnviarComandaCommand(String comandaId) {
		super();
		this.comandaId = comandaId;
	}
  
	public String getComandaId() {
		return comandaId;
	}
    
	

}
