package com.degloba.ecommerce.sales.cqrs.readmodel;

import com.google.appengine.api.datastore.Key;

import java.util.List;

import com.degloba.cqrs.query.PaginatedResult;
import com.degloba.cqrs.query.annotations.Finder;
import com.degloba.ecommerce.sales.cqrs.readmodel.offer.OfferQuery;
import com.degloba.ecommerce.sales.cqrs.readmodel.offer.OfferedProductDto;
import com.degloba.ecommerce.sales.cqrs.readmodel.orders.OrderDto;
import com.degloba.ecommerce.sales.cqrs.readmodel.orders.OrderQuery;


@Finder
public interface ISalesFinder {

	OrderDto find(long orderId);

	PaginatedResult<OrderDto> query(OrderQuery orderQuery);
	
	List<OfferedProductDto> find(OfferQuery query);
	
	
}
