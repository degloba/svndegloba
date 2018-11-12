package com.degloba.ecommerce.sales.cqrs.readmodel.finders;


import java.util.List;

import com.degloba.cqrs.query.PaginatedResult;
import com.degloba.cqrs.query.annotations.Finder;
import com.degloba.domain.persistence.rdbms.jpa.canonicalmodel.publishedlanguage.AggregateId;
import com.degloba.ecommerce.sales.offer.cqrs.readmodel.OfferQuery;
import com.degloba.ecommerce.sales.offer.cqrs.readmodel.dtos.OfferedProductDto;
import com.degloba.ecommerce.sales.orders.cqrs.readmodel.OrderQuery;
import com.degloba.ecommerce.sales.orders.cqrs.readmodel.dtos.OrderDto;




@Finder
public interface ISalesFinder {

	OrderDto find(AggregateId orderId);

	PaginatedResult<OrderDto> query(OrderQuery orderQuery);
	
	List<OfferedProductDto> find(OfferQuery query);
	
	
}
