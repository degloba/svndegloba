package com.degloba.ecommerce.vendes.application.exceptions;


import com.degloba.ecommerce.vendes.ofertes.domain.persistence.rdbms.jpa.Oferta;
import com.degloba.persistence.domain.AggregateId;

@SuppressWarnings("serial")
public class OfferChangedException extends RuntimeException {
	
	private AggregateId comandaId;
	private Oferta seenOffer;
	private Oferta newOffer;
	
	public OfferChangedException(AggregateId comandaId, Oferta seenOffer,
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
