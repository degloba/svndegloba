package com.degloba.ecommerce.crm.cqrs.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Value;

/**
 * @category Command que indica<br>
 * Implementat amb Axon
 * 
 * @author degloba
 *
 */
@Value
public class ConfirmarComandaCommand {
	
	@TargetAggregateIdentifier
    private final String comandaId;

	
}
