package com.degloba.persones.saga;


import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

import com.degloba.ecommerce.eventsourcing.events.PrivateAddressCreatedEvent;
import com.degloba.eventsourcing.persona.PrivateAddressAssignedEvent;
import com.degloba.eventsourcing.persona.PrivateAddressAssignmentRequestedEvent;
import com.degloba.persones.cqrs.commands.AssignPrivateAddressCommand;
import com.degloba.persones.cqrs.commands.CreatePrivateAddressCommand;

@Saga
@Slf4j
@ProcessingGroup("private-address-saga")
public class AssignPrivateAddressSaga {

    @Autowired
    private transient CommandGateway commandGateway;

    public AssignPrivateAddressSaga() {
    }

    @StartSaga
    @SagaEventHandler(associationProperty = "personId")
    public void on(PrivateAddressAssignmentRequestedEvent event) {
        log.debug("[Saga][Person Address][Start] Started saga on event: {}", event);

        // create new private address
        commandGateway.send(new CreatePrivateAddressCommand(event.getAddressId(),
                event.getPersonId(), event.getStreetAndNumber(), event.getZipCode()));
    }

    @SagaEventHandler(associationProperty = "personId")
    public void on(PrivateAddressCreatedEvent event) {
        log.debug("[Saga][Person Address] Private address was created: {}", event);

        // assign created address to a person
        commandGateway.send(new AssignPrivateAddressCommand(event.getPersonaId(), event.getAdrecaId()));
    }

    @EndSaga
    @SagaEventHandler(associationProperty = "personId")
    public void on(PrivateAddressAssignedEvent event) {
        log.debug("[Saga][Person Address][End] Private address was assigned: {}", event);
    }

}
