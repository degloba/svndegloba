package com.degloba.cqrs.commands;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
public class RequestPrivateAddressValidationCommand {

    @TargetAggregateIdentifier
    private final String addressId;

}
