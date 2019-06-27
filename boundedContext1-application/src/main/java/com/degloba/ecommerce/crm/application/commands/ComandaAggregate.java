package com.degloba.ecommerce.crm.application.commands;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateLifecycle;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import com.degloba.ecommerce.vendes.application.events.ComandaFetaEvent;

/**
 * 
 * @category Ara que hem modelat la nostra API central pel que fa a les commands i events, 
 * podem començar a crear el model d’ordres.
 * 
 * L’anotació {@link Aggregate} és una anotació específica d’Axon Spring que marca aquesta classe 
 * com un agregat. Es notificarà al framework que els blocs de construcció específics de CQRS i 
 * de Event Sourcing s’han de crear per a aquesta {@link CommandAggregate}.
 * 
 * Com qualsevol agregat, manipularà Command dirigides a una instància concreta específica, 
 * cal especificar l’identificador amb l’anotació d’AgregacióIdentificador.
 * 
 * El nostre agregat iniciarà el seu cicle de vida després de gestionar el {@link FerComandaCommand} 
 * al "constructor de gestió de comandes" OrderAggregate. Per tal de dir-li al framework que la funció 
 * donada és capaç de gestionar comandes, afegirem l’anotació {@link CommandHandler}.
 * 
 * Quan manipuleu la comanda {@link FerComandaCommand}, 
 * es notificarà a la resta de l’aplicació que s'ha fet una comanda publicant l'event {@link ComandaFetaEvent}.
 * Per publicar un event des d’un agregat, utilitzarem AggregateLifecycle#apply(Object ...).
 * A partir d’aquest moment, realment podem començar a incorporar Event Sourcing com a motor per 
 * recrear una instància agregada del seu flux d’events.
 * Comencem amb l’event de creació d’agregats, el OrderPlacedEvent, que es gestiona en una funció anotada 
 * per EventSourcingHandler per establir l’ordre order idel estat confirmat de l’ordre aggregat.
 * També tingueu en compte que per poder generar un agregat en funció dels seus events, 
 * Axon necessita un constructor per defecte.
 * 
 * @author degloba
 *
 */
@Aggregate
public class ComandaAggregate {

    @AggregateIdentifier
    private String comandaId;
    private boolean orderConfirmed;
 
    @CommandHandler
    public ComandaAggregate(FerComandaCommand command) {
        AggregateLifecycle.apply(new ComandaFetaEvent(command.getComandaId(), command.getProducte()));
    }
 
    @EventSourcingHandler
    public void on(ComandaFetaEvent event) {
        this.comandaId = event.getComandaId();
        orderConfirmed = false;
    }
 
    protected ComandaAggregate() { }
}