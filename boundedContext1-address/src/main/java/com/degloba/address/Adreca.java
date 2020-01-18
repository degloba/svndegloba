package com.degloba.address;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import com.degloba.cqrs.commands.CreatePrivateAddressCommand;
import com.degloba.cqrs.commands.RejectPrivateAddressCommand;
import com.degloba.cqrs.commands.RequestPrivateAddressValidationCommand;
import com.degloba.cqrs.commands.ValidatePrivateAddressCommand;
import com.degloba.ecommerce.eventsourcing.events.PrivateAddressCreatedEvent;
import com.degloba.eventsourcing.persona.PrivateAddressRejectedEvent;
import com.degloba.eventsourcing.persona.PrivateAddressValidatedEvent;
import com.degloba.eventsourcing.persona.PrivateAddressValidationRequestedEvent;

import javax.persistence.*;

@Aggregate
@Entity
@Data
@Slf4j
@ProcessingGroup("address-aggregate")
public class Address {

    enum ValidationStatus {
        Initial,
        Validated,
        Rejected
    }

    @Id
    @AggregateIdentifier
    private String addressId;

    @Column(nullable = false)
    private String personId;

    private String streetAndNumber;

    private String zipCode;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ValidationStatus validationStatus;

    public Address() {
    }

    @CommandHandler
    public Address(CreatePrivateAddressCommand command) {
        log.debug("[Address][Aggregate][Command] Processing create new private address command: {}", command);
        AggregateLifecycle.apply(new PrivateAddressCreatedEvent(command.getAddressId(), command.getPersonId(),
                command.getStreetAndNumber(), command.getZipCode()));
    }

    @CommandHandler
    public void handle(RequestPrivateAddressValidationCommand command) {
        log.debug("[Address][Aggregate][Command] Processing request for private address validation command: {}", command);

        // delegate validation of this address to saga
        AggregateLifecycle.apply(new PrivateAddressValidationRequestedEvent(command.getAddressId(), this.personId));
    }

    @CommandHandler
    public void handle(ValidatePrivateAddressCommand command){
        log.debug("[Address][Aggregate][Command] Processing validate address command: {}", command);

        AggregateLifecycle.apply(new PrivateAddressValidatedEvent(command.getAddressId(), this.personId));
    }

    @CommandHandler
    public void handle(RejectPrivateAddressCommand command){
        log.debug("[Address][Aggregate][Command] Processing reject address command: {}", command);

        AggregateLifecycle.apply(new PrivateAddressRejectedEvent(command.getAddressId(), this.personId));
    }

    @EventHandler
    public void on(PrivateAddressCreatedEvent event) {
        log.debug("[Address][Aggregate][Event] Processing new private address created event: {}", event);
        this.addressId = event.getAddressId();
        this.personId = event.getPersonId();
        this.streetAndNumber = event.getStreetAndNumber();
        this.zipCode = event.getZipCode();
        this.validationStatus = ValidationStatus.Initial;
    }

    @EventHandler
    public void on(PrivateAddressValidatedEvent event) {
        log.debug("[Address][Aggregate][Event] Processing validate address event: {}", event);
        this.validationStatus = ValidationStatus.Validated;
    }

    @EventHandler
    public void on(PrivateAddressRejectedEvent event) {
        log.debug("[Address][Aggregate][Event] Processing reject address event: {}", event);
        this.validationStatus = ValidationStatus.Rejected;
    }
}
