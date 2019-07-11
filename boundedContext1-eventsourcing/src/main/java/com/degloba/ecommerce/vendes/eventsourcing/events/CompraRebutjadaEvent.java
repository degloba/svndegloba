package com.degloba.ecommerce.vendes.eventsourcing.events;

import org.springframework.context.ApplicationEvent;

/**
 * @category  s'ha produit un rebuig d'una compra<br> 
 * Implementada a partir de {@link ApplicationEvent} d'Spring
 * 
 * @author degloba
 *
 */
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
    public String getMessage() {
        return message;
    }
}
