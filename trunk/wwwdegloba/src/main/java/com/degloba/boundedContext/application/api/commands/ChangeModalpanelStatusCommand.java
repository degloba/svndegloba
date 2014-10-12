package com.degloba.boundedContext.application.api.commands;

// DDD
import command.annotations.Command;

import com.degloba.boundedContext.domain.Modalpanel.ModalpanelStatus;

import domain.canonicalmodel.publishedlanguage.AggregateId;

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
