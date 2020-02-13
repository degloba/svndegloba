package com.degloba.cqrs.commands;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
public class ValidatePrivateAddressCommand {

    @TargetAggregateIdentifier
    private final String addressId;

}
