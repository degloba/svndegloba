package com.degloba.ecommerce.vendes.application.listeners;

import org.springframework.context.ApplicationListener;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import com.degloba.ecommerce.vendes.application.events.spring.GenericSpringEvent;

/**
 * @category un listener d'events implementat amb la gestió nativa d'Spring
 * 
 * @author degloba
 *
 */
@Component
public class GenericSpringEventListener implements ApplicationListener<GenericSpringEvent<String>> {
    @Override
    public void onApplicationEvent(@NonNull GenericSpringEvent<String> event) {
        System.out.println("Received spring generic event - " + event.getWhat());
    }
}