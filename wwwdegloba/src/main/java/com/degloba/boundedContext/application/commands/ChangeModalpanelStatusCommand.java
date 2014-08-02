package com.degloba.boundedContext.application.commands;

// DDD
import command.annotations.Command;

import com.degloba.boundedContext.domain.Modalpanel.ModalpanelStatus;

/**
 * @author degloba
 *
 */

@Command
public class ChangeModalpanelStatusCommand<K> {

	private K modalpanelId;

	private ModalpanelStatus status;

	public ChangeModalpanelStatusCommand(K modalpanelId, ModalpanelStatus status) {
		super();
		this.modalpanelId = modalpanelId;
		this.status = status;
	}

	public K getModalpanelId() {
		return modalpanelId;
	}

	public ModalpanelStatus getStatus() {
		return status;
	}

}
