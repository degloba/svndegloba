package com.degloba.ecommerce.comandes.eventsourcing.events;

import lombok.AllArgsConstructor;
import lombok.Value;

/**
 * @category Event produit quan s'ha enviat una comanda
 * 
 * @author degloba
 *
 */
@Value
@AllArgsConstructor
public class ComandaEnviadaEvent {
		 
	private final String comandaId;
	    
}
