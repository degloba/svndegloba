package com.degloba.cqrs.commands;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * 
 * @author degloba
 *
 * @category command en el contexte Axon
 */
@Value
public class AssignPrivateAddressCommand {

    @TargetAggregateIdentifier
    private final String personId;

    private final String addressId;

}
