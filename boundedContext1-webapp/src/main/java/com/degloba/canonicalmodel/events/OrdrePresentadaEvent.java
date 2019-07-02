package com.degloba.canonicalmodel.events;

import java.io.Serializable;

import com.degloba.persistence.rdbms.jpa.AggregateId;


	@SuppressWarnings("serial")
	public class OrdrePresentadaEvent implements Serializable {

	    private final AggregateId ordreId;
	    private final AggregateId enviamentId;

	    public OrdrePresentadaEvent(AggregateId ordreId, AggregateId enviamentId) {
	        this.ordreId = ordreId;
	        this.enviamentId = enviamentId;
	    }

	    public AggregateId getOrdreId() {
	        return ordreId;
	    }

	    public AggregateId getEnviamentId() {
	        return enviamentId;
	    }
	}