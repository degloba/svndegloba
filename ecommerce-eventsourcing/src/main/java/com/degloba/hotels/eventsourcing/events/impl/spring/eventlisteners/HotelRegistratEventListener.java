package com.degloba.hotels.eventsourcing.events.impl.spring.eventlisteners;

import org.springframework.context.ApplicationListener;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import com.degloba.events.impl.spring.domain.Event;


/**
 * @category es un {@link ApplicationListener} d'events implementat amb la gestió nativa d'Spring
 * 
 * @author degloba
 *
 */
@Component
public class HotelRegistratEventListener implements ApplicationListener<Event<HotelRegistratEvent>> {
	
    @Override
    public void onApplicationEvent(@NonNull Event<HotelRegistratEvent> event) {
        System.out.println("Received spring generic event - " + event.getWhat());
    }
}