package com.degloba.ecommerce.sales.readmodel.impl;

import static com.google.common.collect.Lists.transform;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Qualifier;

import query.PaginatedResult;

import com.degloba.annotations.FinderImpl;
//import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.AggregateId;
import com.degloba.ecommerce.sales.purchase.Purchase;
import com.degloba.ecommerce.sales.reservation.Reservation;
import com.degloba.ecommerce.sales.reservation.ReservedProduct;
import com.degloba.ecommerce.sales.readmodel.orders.OrderDto;
import com.degloba.ecommerce.sales.readmodel.orders.OrderFinder;
import com.degloba.ecommerce.sales.readmodel.orders.OrderQuery;
import com.degloba.ecommerce.sales.readmodel.orders.OrderStatus;
import com.degloba.ecommerce.sales.readmodel.orders.OrderedProductDto;
import com.google.appengine.api.datastore.Key;
import com.google.common.base.Function;

@FinderImpl
public class JpaOrderFinder implements OrderFinder {

	@PersistenceContext(unitName="transactions-optional")
    @Qualifier(value="entityManagerFactory")
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
