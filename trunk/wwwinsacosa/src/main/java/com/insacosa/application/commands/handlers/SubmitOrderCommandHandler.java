package com.insacosa.application.commands.handlers;

import java.util.Locale;

import javax.inject.Inject;

import command.handler.CommandHandler;
import command.handler.CommandHandlerAnnotation;
import ddd.application.SystemUser;
import ddd.domain.sharedkernel.Money;
import ddd.domain.sharedkernel.specification.ConjunctionSpecification;
import ddd.domain.sharedkernel.specification.Specification;
import com.insacosa.application.commands.SubmitOrderCommand;
import com.insacosa.domain.Invoice;
import com.insacosa.domain.InvoiceRepository;
import com.insacosa.domain.InvoicingService;
import com.insacosa.domain.Order;
import com.insacosa.domain.OrderRepository;
import com.insacosa.domain.TaxPolicy;
import com.insacosa.domain.errors.OrderOperationException;
import com.insacosa.domain.policies.tax.DefaultTaxPolicy;
import com.insacosa.domain.specification.order.DebtorSpecification;
import com.insacosa.domain.specification.order.DestinationSpecification;
import com.insacosa.domain.specification.order.ItemsCountSpecification;
import com.insacosa.domain.specification.order.RestrictedProductsSpecification;
import com.insacosa.domain.specification.order.TotalCostSpecification;

@CommandHandlerAnnotation
public class SubmitOrderCommandHandler implements CommandHandler<SubmitOrderCommand, Void> {

    @Inject
    private OrderRepository orderRepository;
    
    @Inject
    private InvoiceRepository invoiceRepository;
	
    @Inject
	private InvoicingService invoicingService;
	
    @Inject
	private SystemUser systemUser;

    @Override
    public Void handle(SubmitOrderCommand command) {
        Order order = orderRepository.load(command.getOrderId());
        
        Specification<Order> orderSpecification = generateSpecification(systemUser);
		if (! orderSpecification.isSatisfiedBy(order))
			throw new OrderOperationException("Order does not meet specification", order.getEntityId());            
                		
		//Domain logic
		order.submit();		
		//Domain service
		Invoice invoice = invoicingService.issuance(order, generateTaxPolicy(systemUser));
                
        orderRepository.save(order);
        invoiceRepository.save(invoice);
        
        return null;
    }

    /**
	 * Assembling Spec contains Business Logic, therefore it may be moved to domain Building Block - OrderSpecificationFactory 
	 * 
	 * @param systemUser
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Specification<Order> generateSpecification(SystemUser systemUser) {
		Specification<Order> specification = new ConjunctionSpecification<Order>( 
													new DestinationSpecification(Locale.CHINA),//.not(), // - do not send to China
													new ItemsCountSpecification(100),//max 100 items
													  	new DebtorSpecification()//not debts or max 1000  => debtors can buy for max 1000
															.or(new TotalCostSpecification(new Money(1000.0))));
		
		//vip can buy some nice stuff
		if (! isVip(systemUser)){
			
			specification = specification.and(new RestrictedProductsSpecification());
		}
		
		return specification;
	}



	/**
	 * @param systemUser
	 * @return
	 */
	private boolean isVip(SystemUser systemUser) {
		// TODO Auto-generated method stub
		return false;
	}



	/**
	 * @param systemUser
	 * @return
	 */
	private TaxPolicy generateTaxPolicy(SystemUser systemUser) {
		// TODO determine tax based on user's location
		return new DefaultTaxPolicy();
	}
}
