package com.degloba.ecommerce.eventsourcing.events;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

/**
 * @category s'ha produit quan s'ha fet una comanda
 * 
 * @author degloba
 *
 */
@Value
@Data
@AllArgsConstructor
//@NoArgsConstructor
public class ComandaFetaEvent {

	private final String comandaId;
    private final String producte;
    
}
