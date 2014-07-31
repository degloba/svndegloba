package com.degloba.boundedContext.application.commands;

import command.annotations.Command;

import domain.canonicalmodel.publishedlanguage.AggregateId;

import com.degloba.boundedContext.domain.Modalpanel.ModalpanelStatus;

/**
 * @author Slawek
 *
 */
@SuppressWarnings("serial")
@Command
public class ChangeModalpanelStatusCommand {

	private AggregateId customerId;

	private ModalpanelStatus status;

	public ChangeModalpanelStatusCommand(AggregateId customerId, ModalpanelStatus status) {
		super();
		this.customerId = customerId;
		this.status = status;
	}

	public AggregateId getCustomerId() {
		return customerId;
	}

	public ModalpanelStatus getStatus() {
		return status;
	}

}
