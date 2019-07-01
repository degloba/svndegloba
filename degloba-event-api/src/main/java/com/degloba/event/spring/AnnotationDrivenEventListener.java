package com.degloba.event.spring;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @category També és possible que el listener de l'event amb una condició definint una expressió SpEL booleana 
 * a l’anotació @EventListener. 
 * En aquest cas, el controlador d’events només s’invoca per obtenir un èxit GenericSpringEvent de String
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
