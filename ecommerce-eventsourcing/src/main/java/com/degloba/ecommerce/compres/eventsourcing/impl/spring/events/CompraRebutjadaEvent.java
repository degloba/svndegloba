package com.degloba.ecommerce.compres.eventsourcing.impl.spring.events;

import org.springframework.context.ApplicationEvent;

import com.degloba.events.impl.spring.domain.Event;

import lombok.Value;

/**
 * @category  s'ha produit un rebuig d'una compra<br> 
 * Implementada a partir de {@link ApplicationEvent} d'Spring
 * 
 * @author degloba
 *
 */
public class CompraRebutjadaEvent extends Event {
	
    public CompraRebutjadaEvent(Object source, Object what, boolean success) {
		super(source, what, success);
		// TODO Auto-generated constructor stub
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
 
}
