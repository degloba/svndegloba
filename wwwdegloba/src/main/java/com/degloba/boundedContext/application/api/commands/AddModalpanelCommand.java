package com.degloba.boundedContext.application.api.commands;

import command.annotations.Command;

@Command()
public class AddModalpanelCommand<K> {

	private K modalpanelId;

	public AddModalpanelCommand(K modalpanelId) {
		this.modalpanelId = modalpanelId;
	}

	public K getModalpanelId() {
		return modalpanelId;
	}

	
}
