package com.degloba.person.aggregate;



import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import com.degloba.cqrs.commands.AssignPrivateAddressCommand;
import com.degloba.cqrs.commands.CreatePersonCommand;
import com.degloba.cqrs.commands.RequestPrivateAddressAssignmentCommand;
import com.degloba.eventsourcing.persona.PersonCreatedEvent;
import com.degloba.eventsourcing.persona.PrivateAddressAssignedEvent;
import com.degloba.eventsourcing.persona.PrivateAddressAssignmentRequestedEvent;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@Entity
@Data
@Slf4j
@ProcessingGroup("person-aggregate")
public class Person {

    @Id
    @AggregateIdentifier
    private String id;

    private String fullName;

    private String addressId;


    public Person() {
    }

    // from API
    @CommandHandler
    public Person(CreatePersonCommand command) {
        log.debug("[Person][Aggregate][Command] Creating new person: {}", command);
        apply(new PersonCreatedEvent(command.getPersonId(), command.getFullName()));
    }

    // from API
    @CommandHandler
    public void handle(RequestPrivateAddressAssignmentCommand command) {
        log.debug("[Person][Aggregate][Command] Private address assignment requested: {}", command);
        // should start a saga
        apply(new PrivateAddressAssignmentRequestedEvent(UUID.randomUUID().toString(),
                command.getPersonId(),
                command.getStreetAndNumber(),
                command.getZipCode()));

    }

    // from saga
    @CommandHandler
    public void handle(AssignPrivateAddressCommand command) {
        log.debug("[Person][Aggregate][Command] Assigning private address: {}", command);
        // should end saga
        apply(new PrivateAddressAssignedEvent(command.getPersonId(), command.getAddressId()));
    }

    // domain event
    @EventHandler
    public void on(PersonCreatedEvent event) {
        log.debug("[Person][Aggregate][Event] Person created: {}", event);
        this.id = event.getPersonId();
        this.fullName = event.getFullName();
    }

    // domain event
    @EventHandler
    public void on(PrivateAddressAssignedEvent event) {
        log.debug("[Person][Aggregate][Event] Private address assigned: {}", event);
        this.addressId = event.getAddressId();
    }

}
