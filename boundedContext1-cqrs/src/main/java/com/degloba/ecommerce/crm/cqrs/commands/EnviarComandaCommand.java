package com.degloba.ecommerce.crm.cqrs.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Value;

/**
 * @category Command que indica que s'ha d'enviar la comanda<br>
 * Implementat amb Axon
 * 
 * @author degloba
 *
 */
@Value
public class EnviarComandaCommand {
	
	@TargetAggregateIdentifier
    private final String comandaId;

}
