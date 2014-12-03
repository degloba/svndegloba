package com.degloba.boundedContext.application.api.commands;

// DDD
import command.annotations.Command;

import com.degloba.boundedContext.domain.modules.modalpanel.Modalpanel.ModalpanelStatus;

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
