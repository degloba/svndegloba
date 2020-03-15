package com.degloba.viatges.eventsourcing.events;

import lombok.Data;
import lombok.Value;

/**
 * @category s'ha produit quan s'ha fet una comanda
 * 
 * @author degloba
 *
 */
@Value
@Data
public class ComandaFetaEvent {

	private final String comandaId;
    private final String producte;
    
}
