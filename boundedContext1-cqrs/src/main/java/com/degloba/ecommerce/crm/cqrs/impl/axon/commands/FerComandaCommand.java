package com.degloba.ecommerce.crm.cqrs.impl.axon.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Data;
import lombok.Value;

/**
 * @category Command que definix que s'ha fet una comanda<br>
 * Implementat amb Axon
 * 
 * @author degloba
 *
 */
@Value
@Data
public class FerComandaCommand {

	@TargetAggregateIdentifier
    private final String comandaId;
    private final String producte;
    
    
}
