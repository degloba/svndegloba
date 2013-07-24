package com.insacosa.application.commands.handlers;

import javax.inject.Inject;

import command.handler.CommandHandler;
import command.handler.CommandHandlerAnnotation;
import com.insacosa.application.commands.AddProductToOrderCommand;
import com.insacosa.domain.Order;
import com.insacosa.domain.OrderRepository;
import com.insacosa.domain.Product;
import com.insacosa.domain.ProductRepository;

@CommandHandlerAnnotation
public class AddProductToOrderCommandHandler implements CommandHandler<AddProductToOrderCommand, Void>{

	@Inject
	private OrderRepository orderRepository;

	@Inject
	private ProductRepository productRepository;

	
	@Override
	public Void handle(AddProductToOrderCommand command) {
		Product product = productRepository.load(command.getProductId());
		Order order = orderRepository.load(command.getOrderId());
		
		order.addProduct(product, command.getQuantity());
		
		orderRepository.save(order);
		
		return null;
	}

}
