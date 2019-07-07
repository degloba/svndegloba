package com.degloba.ecommerce.vendes.application.listeners;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.degloba.ecommerce.vendes.application.events.spring.GenericSpringEvent;

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
public class AnnotationDrivenEventListener {
    @EventListener(condition = "#event.success")
    public void handleSuccessful(GenericSpringEvent<String> event) {
        System.out.println("Handling generic event (conditional).");
    }
}
