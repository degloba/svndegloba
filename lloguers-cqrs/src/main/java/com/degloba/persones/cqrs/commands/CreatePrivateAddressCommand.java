package com.degloba.persones.cqrs.commands;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
public class CreatePrivateAddressCommand {

    @TargetAggregateIdentifier
    private final String adrecaId;

    private final String personaId;

    private final String carrerINumero;

    private final String zipCode;

}
