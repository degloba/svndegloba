/**
 * 
 */
package com.insacosa.application.services;

import java.util.Locale;

import javax.inject.Inject;

import ddd.application.ApplicationEventPublisher;
import ddd.application.SystemUser;
import ddd.application.annotation.ApplicationService;
import ddd.domain.sharedkernel.Money;
import ddd.domain.sharedkernel.specification.ConjunctionSpecification;
import ddd.domain.sharedkernel.specification.Specification;
import com.insacosa.application.events.ProductAddedToOrderEvent;
import com.insacosa.domain.Client;
import com.insacosa.domain.Invoice;
import com.insacosa.domain.InvoiceRepository;
import com.insacosa.domain.InvoicingService;
import com.insacosa.domain.Order;
import com.insacosa.domain.OrderFactory;
import com.insacosa.domain.OrderRepository;
import com.insacosa.domain.Product;
import com.insacosa.domain.ProductRepository;
import com.insacosa.domain.TaxPolicy;
import com.insacosa.domain.errors.OrderCreationException;
import com.insacosa.domain.errors.OrderOperationException;
import com.insacosa.domain.policies.tax.DefaultTaxPolicy;
import com.insacosa.domain.specification.order.DebtorSpecification;
import com.insacosa.domain.specification.order.DestinationSpecification;
import com.insacosa.domain.specification.order.ItemsCountSpecification;
import com.insacosa.domain.specification.order.RestrictedProductsSpecification;
import com.insacosa.domain.specification.order.TotalCostSpecification;

/**
 * @author Slawek
 * 
 */
@ApplicationService
public class PurchaseApplicationService {

    @Inject
    private OrderRepository orderRepository;

    @Inject
    private OrderFactory orderFactory;

    @Inject
    private ProductRepository productRepository;

    @Inject
    private InvoiceRepository invoiceRepository;

    @Inject
    private InvoicingService<?, ?> invoicingService;

    @Inject
    private SystemUser systemUser;

    @Inject
    private ApplicationEventPublisher eventPublisher;

    /**
     * Sample usage of factory and repository
     * 
     * @throws OrderCreationException
     */
    public void createNewOrder() throws OrderCreationException {
        Client client = loadClient(systemUser.getUserId());
        Order order = orderFactory.crateOrder(client);
        orderRepository.persist(order);
    }

    /**
     * Sample call of the domain logic<br>
     * Sample publishing Application (not Domain) Event
     * 
     * @param productId
     * @param orderId
     * @param quantity
     */
    public void addProductToOrder(Long productId, Long orderId, int quantity) {
        Order order = orderRepository.load(orderId);
        Product product = productRepository.load(productId);

        // Domain logic
        order.addProduct(product, quantity);

        orderRepository.save(order);

        // if we want to Spy Clients:)
        eventPublisher.publish(new ProductAddedToOrderEvent(product.getEntityId(), systemUser.getUserId(), quantity));
    }

    /**
     * Sample of the separation of domain logic in aggregate and domain logic in
     * domain service
     * 
     * @param orderId
     */
    public void approveOrder(Long orderId) {
        Order order = orderRepository.load(orderId);

        Specification<Order> orderSpecification = generateSpecification(systemUser);
        if (!orderSpecification.isSatisfiedBy(order))
            throw new OrderOperationException("Order does not meet specification", order.getEntityId());

        // Domain logic
        order.submit();
        // Domain service
        Invoice invoice = invoicingService.issuance(order, generateTaxPolicy(systemUser));

        invoiceRepository.save(invoice);
        orderRepository.save(order);
    }

    /**
     * Assembling Spec contains Business Logic, therefore it may be moved to
     * domain Building Block - OrderSpecificationFactory
     * 
     * @param systemUser
     * @return
     */
    @SuppressWarnings("unchecked")
    private Specification<Order> generateSpecification(SystemUser systemUser) {
        Specification<Order> specification = new ConjunctionSpecification<Order>(//
                new DestinationSpecification(Locale.CHINA).not(),// do not send to China
                new ItemsCountSpecification(100),// max 100 items
                new DebtorSpecification()// not debts or max 1000 => debtors can
                                         // buy for max 1000
                        .or(new TotalCostSpecification(new Money(1000.0))));

        // vip can buy some nice stuff
        if (!isVip(systemUser)) {

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

    /**
     * @param userId
     * @return
     */
    private Client loadClient(Long userId) {
        // TODO Auto-generated method stub
        return new Client();
    }
}
