package com.insacosa.application.commands.handlers;

import java.util.Map;

import javax.inject.Inject;

import command.handler.CommandHandler;
import command.handler.CommandHandlerAnnotation;
import ddd.application.ApplicationEventPublisher;
import ddd.application.SystemUser;
import com.insacosa.application.commands.CreateOrderCommand;
import com.insacosa.application.events.ProductAddedToOrderEvent;
import com.insacosa.application.services.PurchaseApplicationService;
import com.insacosa.domain.Client;
import com.insacosa.domain.ClientRepository;
import com.insacosa.domain.Order;
import com.insacosa.domain.OrderFactory;
import com.insacosa.domain.OrderRepository;
import com.insacosa.domain.ProductRepository;
import com.insacosa.domain.errors.OrderCreationException;

/**
 * @see PurchaseApplicationService
 * 
 * @author Rafał Jamróz
 */
@CommandHandlerAnnotation
public class CreateOrderCommandHandler implements
		CommandHandler<CreateOrderCommand, Long> {

	@Inject
	private OrderFactory orderFactory;

	@Inject
	private OrderRepository orderRepository;

	@Inject
	private ProductRepository productRepository;

	@Inject
	private ClientRepository clientRepository;

	@Inject
	private SystemUser systemUser;

	@Inject
	private ApplicationEventPublisher applicationEventPublisher;

	@Override
	public Long handle(CreateOrderCommand command) {
		Client currentClient = clientRepository.load(systemUser.getUserId());

		Order order = orderFactory.crateOrder(currentClient);
		
		for (Map.Entry<Long, Integer> productIdWithCount : command.getProductIdsWithCounts().entrySet()) {
			Long productId = productIdWithCount.getKey();
			Integer count = productIdWithCount.getValue();
			
			order.addProduct(productRepository.load(productId), count);
			
			applicationEventPublisher.publish(new ProductAddedToOrderEvent(productId, systemUser.getUserId(), 1));
		}
		
		orderRepository.persist(order);
		
		return order.getEntityId();
	}
}
