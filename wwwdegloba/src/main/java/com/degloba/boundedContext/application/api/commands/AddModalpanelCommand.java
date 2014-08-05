package com.degloba.boundedContext.application.api.commands;

import command.annotations.Command;

@Command()
public class AddModalpanelCommand {

	private Long modalpanelId;

	public AddModalpanelCommand(Long modalpanelId) {
		this.modalpanelId = modalpanelId;
	}

	public Long getModalpanelId() {
		return modalpanelId;
	}

	
}
