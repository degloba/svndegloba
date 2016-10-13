package com.degloba.ecommerce.crm.application.commands.handlers;

import javax.inject.Inject;

// CQRS
import com.degloba.cqrs.command.annotations.CommandHandlerAnnotation;
import com.degloba.cqrs.command.handler.ICommandHandler;

// Ecommerce
import com.degloba.ecommerce.crm.application.commands.ChangeCustomerStatusCommand;
import com.degloba.ecommerce.crm.domain.Customer;
import com.degloba.ecommerce.crm.domain.persistence.rdbms.jpa.ICrmRepository;



/**
 * @author degloba
 *
 */
@CommandHandlerAnnotation
public class ChangeCustomerStatusCommandHandler implements ICommandHandler<ChangeCustomerStatusCommand>{

	@Inject
	private ICrmRepository crmRepository; 
	
	@Override
	public Void handle(ChangeCustomerStatusCommand command) {
		Customer customer = crmRepository.get(Customer.class,command.getCustomerId());
		customer.changeStatus(command.getStatus());
		crmRepository.save(customer);		
		return null;
	}

}
