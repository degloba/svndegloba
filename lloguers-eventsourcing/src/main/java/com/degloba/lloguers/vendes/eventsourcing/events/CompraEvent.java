package com.degloba.lloguers.vendes.eventsourcing.events;

import com.degloba.domain.events.DomainEvent;

/**
 * @category s'ha produit una compra
 * 
 * @author degloba
 *
 */
public abstract class CompraEvent extends DomainEvent {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected long quantitat;

    public CompraEvent(long quantitat) {
        this.quantitat = quantitat;
    }

    public long getQuantitat() {
        return quantitat;
    }

    @Override
    public String toString() {
        return "CompraEvent{" +
                "quantitat=" + quantitat +
                '}';
    }
}