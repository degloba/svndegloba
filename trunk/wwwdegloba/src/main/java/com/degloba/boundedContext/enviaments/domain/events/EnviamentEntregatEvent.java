package com.degloba.boundedContext.enviaments.domain.events;

import java.io.Serializable;


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