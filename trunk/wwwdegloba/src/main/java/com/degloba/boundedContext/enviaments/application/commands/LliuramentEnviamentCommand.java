package com.degloba.boundedContext.enviaments.application.commands;

import java.io.Serializable;

import command.annotations.Command;
import domain.canonicalmodel.publishedlanguage.AggregateId;



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

