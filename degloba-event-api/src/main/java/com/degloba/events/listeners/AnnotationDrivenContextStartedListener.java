package com.degloba.events.listeners;

import org.springframework.context.event.ContextStartedEvent;
import org.springframework.stereotype.Component;

import com.degloba.events.annotations.EventListener;

/**
 * @category A partir de Spring 4.2, un listener d’events no està obligat a ser un bean que implementi 
 * la interfície ApplicationListener: es pot registrar en qualsevol mètode públic d’un bean administrat 
 * mitjançant l’anotació @EventListener.
 * 
 * @author degloba
 *
 */
@Component
public class AnnotationDrivenContextStartedListener {
    // @Async
    @EventListener
    public void handleContextStart(ContextStartedEvent cse) {
        System.out.println("Handling context started event.");
    }
}
