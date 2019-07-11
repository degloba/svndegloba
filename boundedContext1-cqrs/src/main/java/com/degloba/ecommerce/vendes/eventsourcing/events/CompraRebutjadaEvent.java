package com.degloba.ecommerce.vendes.eventsourcing.events;

import org.springframework.context.ApplicationEvent;

/**
 * @category Event d'aplicació definida a partir de {@link ApplicationEvent} (Spring)
 * 
 * @author degloba
 *
 */
public class CustomSpringEvent extends ApplicationEvent {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
 
    public CustomSpringEvent(Object source, String message) {
        super(source);
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
}
