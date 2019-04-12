package com.degloba.domain.event;


import com.degloba.event.api.IEvent;
import com.degloba.event.api.IEventBus;

/**
 * Interfície d'un Bus d’events de domini. 
 * Event Publisher per crear un Event i, a continuació, l'objecte com a paràmetre del mètode post () de l'event, 
 * esdeveniment es publica al bus d'esdeveniments;
 * Mètode per registrar trucant al subscriptor de l'esdeveniment register () al bus d'esdeveniments, el subscriptor de l'esdeveniment per proporcionar una o més àrees d'un determinat tipus d'esdeveniment
 * Quan l'únic paràmetre i markSubscribe the way, quan el bus en el cas hi hagi un nou tipus de publicació d'esdeveniments i el paràmetre de coincidència del mètode, l'esdeveniment
 * L'autobús serà l'objecte de l'esdeveniment com a argument per cridar a l'esdeveniment un mètode de subscripció de subscriptor.
 */
public interface IDomainEventBus<T extends IEvent> extends IEventBus<T>{

    /**
     * The field event to all registered event subscriber
     * @param event To publish an event
     */
    void publishEvent(DomainEvent event);

    /**
     * Register an event subscription method all subscribers registered to receive events to the event bus.
     * @param subscriber Event subscribers. Event subscriber must implement one or more instances of a sub-class of a field event as the only parameter
     * And labeled asSubscribe approach.
     */
    void registerSubscriber(Object subscriber);

    /**
     * Deregistration a registered events to the event bus subscribers all subscription methods.
     * @param subscriber Event subscribers.
     */
    void unregisterSubscriber(Object subscriber);
}
