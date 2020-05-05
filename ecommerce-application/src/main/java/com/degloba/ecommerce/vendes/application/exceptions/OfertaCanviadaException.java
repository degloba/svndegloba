package com.degloba.ecommerce.vendes.application.exceptions;


import com.degloba.ecommerce.vendes.ofertes.domain.persistence.rdbms.jpa.Oferta;
import com.degloba.persistence.rdbms.api.jpa.AggregateId;

/**
 * @category 
 * 
 * @author degloba
 *
 */
@SuppressWarnings("serial")
public class OfertaCanviadaException extends RuntimeException {
	
	private AggregateId comandaId;
	private Oferta seenOffer;
	private Oferta newOffer;
	
	public OfertaCanviadaException(AggregateId comandaId, Oferta seenOffer,
			Oferta newOffer) {
		this.comandaId = comandaId;
		this.seenOffer = seenOffer;
		this.newOffer = newOffer;
	}
	
	public AggregateId getComandaId() {
		return comandaId;
	}
	
	public Oferta getSeenOffer() {
		return seenOffer;
	}
	
	public Oferta getNewOffer() {
		return newOffer;
	}

}
