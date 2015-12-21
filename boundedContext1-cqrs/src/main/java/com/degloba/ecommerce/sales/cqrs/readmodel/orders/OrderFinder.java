package com.degloba.ecommerce.sales.cqrs.readmodel.orders;

import com.google.appengine.api.datastore.Key;

import com.degloba.cqrs.query.PaginatedResult;
import com.degloba.cqrs.query.annotations.Finder;


@Finder
public interface OrderFinder {

	OrderDto find(Key orderId);

	PaginatedResult<OrderDto> query(OrderQuery orderQuery);
}
