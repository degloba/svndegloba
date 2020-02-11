package com.degloba.cqrs.commands;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
public class RejectPrivateAddressCommand {

    @TargetAggregateIdentifier
    private final String adrecaId;

}