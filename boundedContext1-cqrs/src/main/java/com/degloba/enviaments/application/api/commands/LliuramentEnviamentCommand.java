package com.degloba.enviaments.application.api.commands;

import java.io.Serializable;

import command.annotations.Command;
import com.degloba.domain.canonicalmodel.publishedlanguage.AggregateId;

	@SuppressWarnings("serial")
	@Command
	public class LliuramentEnviamentCommand implements Serializable {

	    private final AggregateId shipmentId;

	    public LliuramentEnviamentCommand(AggregateId shipmentId) {
	        this.shipmentId = shipmentId;
	    }

	    public AggregateId getShipmentId() {
	        return shipmentId;
	    }
	}

