package com.degloba.ecommerce.eventuate;

import java.util.List;

import com.networknt.eventuate.common.Aggregate;
import com.networknt.eventuate.common.Command;
import com.networknt.eventuate.common.CommandProcessingAggregate;
import com.networknt.eventuate.common.Event;

public class Customer implements CommandProcessingAggregate {

	@Override
	public Aggregate applyEvent(Event event) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List processCommand(Command cmd) {
		// TODO Auto-generated method stub
		return null;
	}

}
