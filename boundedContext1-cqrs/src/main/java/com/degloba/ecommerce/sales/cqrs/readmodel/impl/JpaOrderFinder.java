package com.degloba.ecommerce.sales.cqrs.readmodel.impl;

import static com.google.common.collect.Lists.transform;

import java.util.ArrayList;
import java.util.List;

// JPA
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

// Spring
import org.springframework.beans.factory.annotation.Qualifier;

// CQRS
import com.degloba.cqrs.query.PaginatedResult;

import com.degloba.domain.annotations.FinderImpl;

// Ecommerce
import com.degloba.ecommerce.sales.purchase.domain.Purchase;
import com.degloba.ecommerce.sales.reservation.domain.Reservation;
import com.degloba.ecommerce.sales.reservation.domain.ReservedProduct;

// CQRS (ecommerce)
import com.degloba.ecommerce.sales.cqrs.readmodel.orders.OrderDto;
import com.degloba.ecommerce.sales.cqrs.readmodel.orders.OrderFinder;
import com.degloba.ecommerce.sales.cqrs.readmodel.orders.OrderQuery;
import com.degloba.ecommerce.sales.cqrs.readmodel.orders.OrderStatus;
import com.degloba.ecommerce.sales.cqrs.readmodel.orders.OrderedProductDto;

// Google app engine
import com.google.appengine.api.datastore.Key;
import com.google.common.base.Function;

@FinderImpl
public class JpaOrderFinder implements OrderFinder {

	@PersistenceContext(unitName="transactions-optional")
    @Qualifier(value="entityManagerFactoryDatastore")
	private EntityManager entityManager;

	@Override
	public OrderDto find(Key orderId) {
		Reservation reservation = entityManager.find(Reservation.class, orderId);
		Purchase purchase = entityManager.find(Purchase.class, orderId);
		
		return toOrderDto(reservation, purchase);
	}

	private OrderDto toOrderDto(Reservation reservation, Purchase purchase) {
		OrderDto dto = new OrderDto();
		dto.setOrderId(reservation.getAggregateId());
		List<ReservedProduct> reservedProducts = reservation.getReservedProducts();
		dto.setOrderedProducts(new ArrayList<OrderedProductDto>(transform(reservedProducts,
				reservedProductToOrderedProductDto())));
		if (purchase != null) {
			dto.setStatus(OrderStatus.CONFIRMED);

			// TODO CHECK PAYMENT!
			
		} else {
			dto.setStatus(OrderStatus.NEW);
		}
		return dto;
	}

	private static Function<ReservedProduct, OrderedProductDto> reservedProductToOrderedProductDto() {
		return new Function<ReservedProduct, OrderedProductDto>() {
			public OrderedProductDto apply(ReservedProduct product) {
				OrderedProductDto dto = new OrderedProductDto();
				dto.setOfferId(product.getProductId());
				return dto;
			}
		};
	}

	@Override
	public PaginatedResult<OrderDto> query(OrderQuery orderQuery) {
		return null;
	}

}
