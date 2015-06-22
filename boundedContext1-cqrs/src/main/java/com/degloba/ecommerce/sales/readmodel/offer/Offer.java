package com.degloba.ecommerce.sales.readmodel.offer;

import java.util.List;

import query.annotations.Finder;

@Finder
public interface Offer {

	public List<OfferedProductDto> find(OfferQuery query);
}
