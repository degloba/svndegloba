package com.degloba.ecommerce.crm.application.commands.handlers;

import javax.inject.Inject;

// CQRS
import com.degloba.cqrs.command.annotations.CommandHandlerAnnotation;
import com.degloba.cqrs.command.handler.ICommandHandler;

// Ecommerce
import com.degloba.ecommerce.crm.application.commands.ChangeCustomerStatusCommand;
import com.degloba.ecommerce.crm.domain.persistence.rdbms.jpa.Customer;
import com.degloba.ecommerce.crm.domain.persistence.rdbms.jpa.ICrmRepository;


/**
 * @author degloba
 * 
 * @category Modifica l'estat d'un {@link Customer} a partir del {@link Command} {@link ChangeCustomerStatusCommand}
 *
 */
@CommandHandlerAnnotation
public class ChangeCustomerStatusCommandHandler implements ICommandHandler<ChangeCustomerStatusCommand,Boolean>{

	@Inject
	private ICrmRepository crmRepository; 
	
	@Override
	public Boolean handle(ChangeCustomerStatusCommand command) {
		Customer customer = crmRepository.get(Customer.class,command.getCustomerId());
		customer.changeStatus(command.getStatus());
		crmRepository.save(customer);		
		return null;
	}

}
