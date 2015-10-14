package com.degloba.ecommerce.crm.application.commands.handlers;

import javax.inject.Inject;

import command.annotations.CommandHandlerAnnotation;
import command.handler.ICommandHandler;
import com.degloba.ecommerce.crm.application.commands.ChangeCustomerStatusCommand;
import com.degloba.ecommerce.crm.domain.Customer;
import com.degloba.ecommerce.crm.domain.ICustomerRepository;

/**
 * @author degloba
 *
 */
@CommandHandlerAnnotation
public class ChangeCustomerStatusCommandHandler implements ICommandHandler<ChangeCustomerStatusCommand>{

	@Inject
	private ICustomerRepository customerRepository; 
	
	@Override
	public Void handle(ChangeCustomerStatusCommand command) {
		Customer customer = customerRepository.load(command.getCustomerId());
		customer.changeStatus(command.getStatus());
		customerRepository.save(customer);		
		return null;
	}

}
