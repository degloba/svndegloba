package com.degloba.axon_multi.address.aggregates;


import com.degloba.cqrs.commands.CreatePrivateAddressCommand;
import com.degloba.eventsourcing.axon_multi.events.PrivateAddressCreatedEvent;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import javax.persistence.Entity;
import javax.persistence.Id;

@Aggregate
@Entity
@Data
@Slf4j
@ProcessingGroup("address-aggregate")
public class Address {

    @Id
    @AggregateIdentifier
    private String addressId;

    private String personId;

    private String streetAndNumber;

    private String zipCode;

    public Address() {
    }

    @CommandHandler
    public Address(CreatePrivateAddressCommand command) {
        log.debug("[Address][Aggregate][Command] Processing create new private address command: {}", command);
        AggregateLifecycle.apply(new PrivateAddressCreatedEvent(command.getAddressId(), command.getPersonId(),
                command.getStreetAndNumber(), command.getZipCode()));
    }

    @EventHandler
    public void on(PrivateAddressCreatedEvent event) {
        log.debug("[Address][Aggregate][Event] Processing new private address created event: {}", event);
        this.addressId = event.getAddressId();
        this.personId = event.getPersonId();
        this.streetAndNumber = event.getStreetAndNumber();
        this.zipCode = event.getZipCode();
    }
}