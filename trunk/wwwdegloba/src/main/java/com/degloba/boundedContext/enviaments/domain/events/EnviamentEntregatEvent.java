package com.degloba.boundedContext.enviaments.domain.events;

import java.io.Serializable;

import domain.canonicalmodel.publishedlanguage.AggregateId;

	@SuppressWarnings("serial")
	public class EnviamentEntregatEvent implements Serializable {

	    private final Long enviamentId;

	    public EnviamentEntregatEvent(Long enviamentId) {
	        this.enviamentId = enviamentId;
	    }

	    public Long getEnviamentId() {
	        return enviamentId;
	    }
	}