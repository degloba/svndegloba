package com.degloba.ecommerce.eventsourcing.events;

/**
 * @category s'ha produit quan s'ha fet una comanda
 * 
 * @author degloba
 *
 */
public class ComandaFetaEvent {

	private final String comandaId;
    private final String producte;
    
    // default constructor, getters, equals/hashCode and toString    
	public ComandaFetaEvent(String comandaId, String producte) {
		super();
		this.comandaId = comandaId;
		this.producte = producte;
	}
	public String getComandaId() {
		return comandaId;
	}
	public String getProducte() {
		return producte;
	}
  
   
}
