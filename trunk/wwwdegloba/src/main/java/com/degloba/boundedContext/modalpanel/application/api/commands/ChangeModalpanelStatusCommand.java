package com.degloba.boundedContext.modalpanel.application.api.commands;

// DDD
import command.annotations.Command;

import domain.canonicalmodel.publishedlanguage.AggregateId;

/**
 * @author degloba
 *
 */

@Command
public class ChangeModalpanelStatusCommand{

	private Long modalpanelId;

	private ModalpanelStatus status;

	public ChangeModalpanelStatusCommand(Long modalpanelId, ModalpanelStatus status) {
		super();
		this.modalpanelId = modalpanelId;
		this.status = status;
	}

	public Long getModalpanelId() {
		return modalpanelId;
	}

	public ModalpanelStatus getStatus() {
		return status;
	}

}