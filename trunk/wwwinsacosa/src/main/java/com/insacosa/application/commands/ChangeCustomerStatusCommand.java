/**
 * 
 */
package com.insacosa.application.commands;

import java.io.Serializable;

import command.Command;
import com.insacosa.domain.Customer.CustomerStatus;

/**
 * @author Slawek
 *
 */
@SuppressWarnings("serial")
@Command
public class ChangeCustomerStatusCommand implements Serializable{

	private Long customerId;
	
	private CustomerStatus status;

	public ChangeCustomerStatusCommand(Long customerId, CustomerStatus status) {
		super();
		this.customerId = customerId;
		this.status = status;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public CustomerStatus getStatus() {
		return status;
	}
	
	
}
