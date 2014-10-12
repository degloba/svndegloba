package com.degloba.boundedContext.application.api.commands;

import command.annotations.Command;
import domain.canonicalmodel.publishedlanguage.AggregateId;

@Command()
public class AddModalpanelCommand {
	
	private AggregateId modalpanelId;

	public AddModalpanelCommand(AggregateId aggregateId) {
		// TODO Auto-generated constructor stub
		this.modalpanelId=aggregateId;
	}

	public AggregateId getModalpanelId() {
		return modalpanelId;
	}

	
}
