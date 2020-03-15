package com.degloba.persones.cqrs.commands;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * @category command contexte Axon
 * 
 * @author degloba
 *
 */
@Value
public class CreaPersonaCommand {

    @TargetAggregateIdentifier
    private final String personaId;

    private final String nomComplet;

}