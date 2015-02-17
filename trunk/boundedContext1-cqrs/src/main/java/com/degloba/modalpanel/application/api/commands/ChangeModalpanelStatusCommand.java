package com.degloba.modalpanel.application.api.commands;

// DDD
import command.annotations.Command;

import com.degloba.casino.modalpanel.Modalpanel.ModalpanelStatus;
import com.degloba.domain.canonicalmodel.publishedlanguage.AggregateId;


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
