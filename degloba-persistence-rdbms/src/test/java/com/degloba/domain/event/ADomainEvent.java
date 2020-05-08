package com.degloba.domain.event;

import java.util.Date;

public abstract class ADomainEvent {

	public ADomainEvent(Date occurredOn) {
		// TODO Auto-generated constructor stub
	}

	public ADomainEvent(Date occurredOn, int version) {
		// TODO Auto-generated constructor stub
	}

	public void setId(String id) {
		// TODO Auto-generated method stub
		
	}

	public Object getVersion() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getOccurredOn() {
		// TODO Auto-generated method stub
		return null;
	}

}
