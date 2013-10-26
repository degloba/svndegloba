/**
 * 
 */
package com.insacosa.application.commands.handlers;

import javax.inject.Inject;

import command.handler.CommandHandler;
import command.handler.CommandHandlerAnnotation;
import com.insacosa.application.commands.ChangeCustomerStatusCommand;
import com.insacosa.domain.Customer;
import com.insacosa.domain.repositories.CustomerRepository;

/**
 * @author Slawek
 *
 */
@CommandHandlerAnnotation
public class ChangeCustomerStatusCommandHandler implements CommandHandler<ChangeCustomerStatusCommand, Void>{

	@Inject
	private CustomerRepository customerRepository; 
	
	public Void handle(ChangeCustomerStatusCommand command) {
		Customer customer = customerRepository.load(command.getCustomerId());
		customer.changeStatus(command.getStatus());
		customerRepository.save(customer);		
		return null;
	}

}
