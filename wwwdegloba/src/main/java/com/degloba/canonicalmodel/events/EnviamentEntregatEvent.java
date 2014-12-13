package com.degloba.canonicalmodel.events;

import java.io.Serializable;

import domain.canonicalmodel.publishedlanguage.AggregateId;

	@SuppressWarnings("serial")
	public class EnviamentEntregatEvent implements Serializable {

	    private final AggregateId enviamentId;

	    public EnviamentEntregatEvent(AggregateId enviamentId) {
	        this.enviamentId = enviamentId;
	    }

	    public AggregateId getEnviamentId() {
	        return enviamentId;
	    }
	}