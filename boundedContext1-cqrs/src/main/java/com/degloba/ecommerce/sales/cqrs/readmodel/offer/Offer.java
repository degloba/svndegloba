package com.degloba.ecommerce.sales.cqrs.readmodel.offer;

import java.util.List;

// CQRS
import com.degloba.cqrs.query.annotations.Finder;

@Finder
public interface Offer {

	public List<OfferedProductDto> find(OfferQuery query);
}
