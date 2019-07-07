package com.degloba.ecommerce.crm.cqrs.commands;

import org.axonframework.commandhandling.CommandHandler;

import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import com.degloba.ecommerce.crm.cqrs.commands.ConfirmarComandaCommand;
import com.degloba.ecommerce.crm.cqrs.commands.EnviarComandaCommand;
import com.degloba.ecommerce.crm.cqrs.commands.FerComandaCommand;
import com.degloba.ecommerce.crm.cqrs.events.ComandaConfirmadaEvent;
import com.degloba.ecommerce.crm.cqrs.events.ComandaEnviadaEvent;
import com.degloba.ecommerce.vendes.cqrs.events.ComandaFetaEvent;

/**
 * 
 * @category L’anotació {@link Aggregate} és una anotació específica d’Axon Spring. 
 * Es notificarà al framework que els blocs de construcció específics de CQRS i 
 * d'Event Sourcing s’han de crear per a aquesta {@link CommandAggregate}.
 * 
 * Com qualsevol agregat, manipularà {@link Command} dirigides a una instància concreta específica 
 * Cal especificar l’identificador amb l’anotació d’AgregacióIdentificador.
 * 
 * El nostre agregat iniciarà el seu cicle de vida després de gestionar el {@link FerComandaCommand} 
 * al "constructor de gestió de comandes" OrderAggregate. Per tal de dir-li al framework que la funció 
 * donada és capaç de gestionar comandes, afegirem l’anotació {@link CommandHandler}.
 * 
 * En el handle de la comanda {@link FerComandaCommand}, es notificarà a la resta de l’aplicació 
 * que s'ha fet una comanda publicant l'event {@link ComandaFetaEvent}.
 * Per publicar un event des d’un agregat, utilitzarem AggregateLifecycle#apply(Object ...).
 * A partir d’aquest moment, realment podem començar a incorporar Event Sourcing com a motor per 
 * recrear una instància agregada del seu flux d’events.
 * 
 * Comencem amb l’event de creació d’agregats, el ComandaFetaEvent, que es gestiona en una funció anotada 
 * per EventSourcingHandler per establir l’ordre order idel estat confirmat de l’ordre aggregat.
 * Cal tenir en compte que per poder generar un agregat en funció dels seus events, 
 * Axon necessita un constructor per defecte.
 * 
 * @author degloba
 *
 */
@Aggregate
public class ComandaAggregate {

    @AggregateIdentifier
    private String comandaId;
    private boolean comandaConfirmada;
 
    @CommandHandler
    public ComandaAggregate(FerComandaCommand command) {
        AggregateLifecycle.apply(new ComandaFetaEvent(command.getComandaId(), command.getProducte()));
    }
    
    @CommandHandler
    public void handle(ConfirmarComandaCommand command) { 
    	AggregateLifecycle.apply(new ComandaConfirmadaEvent(comandaId)); 
    } 
     
    @CommandHandler
    public void handle(EnviarComandaCommand command) { 
        if (!comandaConfirmada) { 
            throw new IllegalStateException("Cannot ship an order which has not been confirmed yet."); 
        } 
        AggregateLifecycle.apply(new ComandaEnviadaEvent(comandaId)); 
    } 
     
    @EventSourcingHandler
    public void on(ComandaConfirmadaEvent event) { 
    	comandaConfirmada = true; 
    }
 
    @EventSourcingHandler
    public void on(ComandaFetaEvent event) {
        this.comandaId = event.getComandaId();
        comandaConfirmada = false;
    }
 
    protected ComandaAggregate() { }
}