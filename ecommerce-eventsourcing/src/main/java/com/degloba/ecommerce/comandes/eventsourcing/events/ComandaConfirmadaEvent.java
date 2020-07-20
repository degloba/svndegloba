package com.degloba.ecommerce.comandes.eventsourcing.events;

import lombok.AllArgsConstructor;
import lombok.Value;

/**
 * @category s'ha produit al confirmar una comanda
 * 
 * @author degloba
 *
 */
@Value
@AllArgsConstructor
public class ComandaConfirmadaEvent {
	
	String comandaId; 
	
}
