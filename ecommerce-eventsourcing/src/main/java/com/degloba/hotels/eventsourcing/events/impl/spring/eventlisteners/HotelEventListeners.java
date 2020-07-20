package com.degloba.hotels.eventsourcing.events.impl.spring.eventlisteners;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.degloba.events.impl.spring.domain.Event;


/**
 * @category Listener d'events implementat amb Spring natiu<br>
 * És possible afegir una condició booleana definida amb una expressió 
 * SpEL dins l’anotació @EventListener. 
 * En aquest cas, el controlador d’events només s’invoca per obtenir un èxit GenericSpringEvent de String
 * 
 * @author degloba
 *
 */
@Component
public class HotelEventListeners {
	
    @EventListener(condition = "#event.success")
    public void handleSuccessful(Event<HotelRegistratEvent> event) {
        System.out.println("Handling generic event (conditional).");
    }
    
    @EventListener(condition = "#event.fail")
    public void handleFail(Event<HotelNoRegistratEvent> event) {
        System.out.println("Handling generic event (conditional).");
    }
    
    // TODO
    
    
}
