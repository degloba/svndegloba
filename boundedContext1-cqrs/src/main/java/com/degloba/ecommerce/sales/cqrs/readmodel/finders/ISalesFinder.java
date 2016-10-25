package com.degloba.ecommerce.sales.cqrs.readmodel.finders;


import java.util.List;

import com.degloba.cqrs.query.PaginatedResult;
import com.degloba.cqrs.query.annotations.Finder;
import com.degloba.ecommerce.sales.offer.OfferQuery;
import com.degloba.ecommerce.sales.offer.dtos.OfferedProductDto;
import com.degloba.ecommerce.sales.orders.OrderQuery;
import com.degloba.ecommerce.sales.orders.dtos.OrderDto;


@Finder
public interface ISalesFinder {

	OrderDto find(long orderId);

	PaginatedResult<OrderDto> query(OrderQuery orderQuery);
	
	List<OfferedProductDto> find(OfferQuery query);
	
	
}
