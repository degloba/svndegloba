package com.degloba.viatges.vendes.eventsourcing.events;

import com.degloba.viatges.vendes.eventsourcing.events.CompraEvent;

/**
 * @category s'ha produit una compra en efectiu
 * 
 * @author degloba
 *
 */
public class CompraEnEfectiuEvent extends CompraEvent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String item;

    public CompraEnEfectiuEvent(long quantitat, String item) {
        super(quantitat);
        this.item = item;
    }

    public String getItem() {
        return item;
    }

    @Override
    public String toString() {
        return "CompraEnEfectiuEvent{" +
                "item='" + item + '\'' +
                "} " + super.toString();
    }
}
