package com.degloba.lloguers.eventsourcing.events;

import lombok.Value;

/**
 * @category s'ha produit quan s'ha enviat una comanda
 * 
 * @author degloba
 *
 */
@Value
public class ComandaEnviadaEvent {
		 
	    private final String comandaId;

		public ComandaEnviadaEvent(String comandaId) {
			// TODO Auto-generated constructor stub
			this.comandaId = comandaId;
		}
	    
}
