package com.degloba.ecommerce.sales.readmodel.orders;

import com.google.appengine.api.datastore.Key;

import query.PaginatedResult;
import query.annotations.Finder;
//import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.AggregateId;

@Finder
public interface OrderFinder {

	OrderDto find(Key orderId);

	PaginatedResult<OrderDto> query(OrderQuery orderQuery);
}
