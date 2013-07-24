package com.insacosa.presentation.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import query.annotations.Finder;
import com.insacosa.domain.Order;
import com.insacosa.presentation.ClientOrderDetailsDto;
import com.insacosa.presentation.ClientOrderListItemDto;
import com.insacosa.presentation.OrderFinder;

/**
 * @author Rafał Jamróz
 */
@Finder
public class JpaOrderFinder implements OrderFinder {

    @PersistenceContext
    private EntityManager entityManager;

    public ClientOrderDetailsDto getClientOrderDetails(Long orderId) {
        Order order = entityManager.find(Order.class, orderId);
        return order == null ? null : toOrderDetailsDto(order);
    }

    private ClientOrderDetailsDto toOrderDetailsDto(Order order) {
        ClientOrderDetailsDto dto = new ClientOrderDetailsDto();
        dto.setOrderId(order.getEntityId());
        dto.setTotalCost(order.getTotalCost());
        dto.setOrderedProducts(order.getOrderedProducts());
        dto.setSubmitDate(order.getSubmitDate());
        dto.setOrderStatus(order.getStatus());
        return dto;
    }

    @SuppressWarnings("unchecked")
    public List<ClientOrderListItemDto> findOrders() {
        Query query = entityManager
                .createQuery("select new sales.presentation.ClientOrderListItemDto("
                        + "o.id, o.totalCost, o.submitDate, o.status) from Order o");
        return query.getResultList();
    }
}
