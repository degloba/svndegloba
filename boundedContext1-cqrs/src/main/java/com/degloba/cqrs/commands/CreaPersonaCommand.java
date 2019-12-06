package com.degloba.cqrs.commands;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * @category
 * 
 * @author degloba
 *
 */
@Value
public class CreaPersonaCommand {

    @TargetAggregateIdentifier
    private final String personId;

    private final String fullName;

}