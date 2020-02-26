package com.degloba.person.aggregate;



import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import com.degloba.eventsourcing.persona.PersonaCreadaEvent;
import com.degloba.eventsourcing.persona.PrivateAddressAssignedEvent;
import com.degloba.eventsourcing.persona.PrivateAddressAssignmentRequestedEvent;
import com.degloba.persones.cqrs.commands.AssignPrivateAddressCommand;
import com.degloba.persones.cqrs.commands.CreaPersonaCommand;
import com.degloba.persones.cqrs.commands.RequestPrivateAddressAssignmentCommand;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

/**
 * 
 * @author degloba
 * 
 * Aggregate --> contexte Axon
 * Entity --> contexte JPA
 * Vincula un command a un event en el contexte Axon
 * 
 * @category 
 *
 */
@Aggregate
@Entity
@Data
@Slf4j
@ProcessingGroup("person-aggregate")
public class Person {

    @Id
    @AggregateIdentifier
    private String id;

    private String nomComplet;

    private String adrecaId;


    public Person() {
    }

    // from API
    @CommandHandler
    public Person(CreaPersonaCommand command) {
        log.debug("[Person][Aggregate][Command] Creating new person: {}", command);
        apply(new PersonaCreadaEvent(command.getPersonaId(), command.getNomComplet()));
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
    public void on(PersonaCreadaEvent event) {
        log.debug("[Person][Aggregate][Event] Person created: {}", event);
        this.id = event.getPersonaId();
        this.nomComplet = event.getNomComplet();
    }

    // domain event
    @EventHandler
    public void on(PrivateAddressAssignedEvent event) {
        log.debug("[Person][Aggregate][Event] Private address assigned: {}", event);
        this.adrecaId = event.getAdrecaId();
    }

}
