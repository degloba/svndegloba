package com.degloba.ecommerce.crm.application.commands;

import java.io.Serializable;

import com.google.appengine.api.datastore.Key;

import command.annotations.Command;
//import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.AggregateId;
import com.degloba.ecommerce.crm.domain.Customer.CustomerStatus;

/**
 * @author degloba
 *
 */
@SuppressWarnings("serial")
@Command
public class ChangeCustomerStatusCommand implements Serializable{

	private Key customerId;
	
	private CustomerStatus status;

	public ChangeCustomerStatusCommand(Key customerId, CustomerStatus status) {
		super();
		this.customerId = customerId;
		this.status = status;
	}

	public Key getCustomerId() {
		return customerId;
	}

	public CustomerStatus getStatus() {
		return status;
	}
	
	
}
