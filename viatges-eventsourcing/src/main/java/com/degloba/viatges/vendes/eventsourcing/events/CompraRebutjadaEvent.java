package com.degloba.viatges.vendes.eventsourcing.events;

import org.springframework.context.ApplicationEvent;

import lombok.Value;

/**
 * @category  s'ha produit un rebuig d'una compra<br> 
 * Implementada a partir de {@link ApplicationEvent} d'Spring
 * 
 * @author degloba
 *
 */
@Value
public class CompraRebutjadaEvent extends ApplicationEvent {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
 
    public CompraRebutjadaEvent(Object source, String message) {
        super(source);
        this.message = message;
    }

}