package com.degloba.ecommerce.compres.eventsourcing.events;

import com.degloba.domain.events.DomainEvent;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @category s'ha produit una compra
 * 
 * @author degloba
 *
 */
@AllArgsConstructor
@ToString
public abstract class CompraEvent extends DomainEvent {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Getter protected long quantitat;

}