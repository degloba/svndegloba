package com.degloba.casino.enviaments.events;

import java.io.Serializable;

import com.google.appengine.api.datastore.Key;


	@SuppressWarnings("serial")
	public class EnviamentEntregatEvent implements Serializable {

	    private final Key enviamentId;

	    public EnviamentEntregatEvent(Key enviamentId) {
	        this.enviamentId = enviamentId;
	    }

	    public Key getEnviamentId() {
	        return enviamentId;
	    }
	}