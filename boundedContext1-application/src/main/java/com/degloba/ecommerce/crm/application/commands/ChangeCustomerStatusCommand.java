package com.degloba.ecommerce.crm.application.commands;

import java.io.Serializable;

import com.google.appengine.api.datastore.Key;

//CQRS
import com.degloba.cqrs.command.annotations.Command;

// Domain

import com.degloba.ecommerce.crm.domain.persistence.rdbms.jpa.Customer.CustomerStatus;

/**
 * @author degloba
 *
 */
@SuppressWarnings("serial")
@Command
public class ChangeCustomerStatusCommand implements Serializable{

	private long customerId;
	
	private CustomerStatus status;

	public ChangeCustomerStatusCommand(long customerId, CustomerStatus status) {
		super();
		this.customerId = customerId;
		this.status = status;
	}

	public long getCustomerId() {
		return customerId;
	}

	public CustomerStatus getStatus() {
		return status;
	}
	
	
}
