package com.degloba.ecommerce.compres.eventsourcing.events;


import lombok.Getter;
import lombok.ToString;

/**
 * @category s'ha produit una compra en efectiu
 * 
 * @author degloba
 *
 */
@ToString
public class CompraEnEfectiuEvent extends CompraEvent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Getter private String item;

    public CompraEnEfectiuEvent(long quantitat, String item) {
        super(quantitat);
        this.item = item;
    }

}
