/**
 * 
 */
package com.insacosa.infrastructure.repositories.jpa;

import javax.inject.Inject;

import com.insacosa.domain.Order;
import com.insacosa.domain.OrderRepository;
import com.insacosa.domain.RebatePolicyFactory;
import com.insacosa.domain.events.OrderCreatedEvent;

import ddd.domain.DomainEventPublisher;
import ddd.domain.annotations.DomainRepositoryImpl;
import ddd.domain.support.InjectorHelper;
import ddd.infrastructure.repo.jpa.GenericJpaRepositoryForBaseEntity;


/**
 * @author Slawek
 * 
 */
@DomainRepositoryImpl
public class JpaOrderRepository extends GenericJpaRepositoryForBaseEntity<Order> implements OrderRepository {

    @Inject
    private RebatePolicyFactory rebatePolicyFactory;
    @Inject
    private InjectorHelper injector;
    @Inject DomainEventPublisher eventPublisher;
    
    @Override
    public void persist(Order order) {    	
    	super.persist(order);
    	 eventPublisher.publish(new OrderCreatedEvent(order.getEntityId()));
    }

    @Override
    public Order load(Long orderId) {
        Order order = super.load(orderId);
        injector.injectDependencies(order);
        order.setRebatePolicy(rebatePolicyFactory.createRebatePolicy());
        return order;
    }
}
