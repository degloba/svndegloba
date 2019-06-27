/**
 * 
 */
package com.degloba.ecommerce.crm.application.commands;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

/**
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
