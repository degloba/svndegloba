package com.degloba.boundedContext.modalpanel.application.api.commands;

// DDD
import command.annotations.Command;

import domain.canonicalmodel.publishedlanguage.AggregateId;

import com.degloba.boundedContext.modalpanel.domain.Modalpanel.ModalpanelStatus;

/**
 * @author degloba
 *
 */

@Command
public class ChangeModalpanelStatusCommand{

	private AggregateId modalpanelId;

	private ModalpanelStatus status;

	public ChangeModalpanelStatusCommand(AggregateId modalpanelId, ModalpanelStatus status) {
		super();
		this.modalpanelId = modalpanelId;
		this.status = status;
	}

	public AggregateId getModalpanelId() {
		return modalpanelId;
	}

	public ModalpanelStatus getStatus() {
		return status;
	}

}
