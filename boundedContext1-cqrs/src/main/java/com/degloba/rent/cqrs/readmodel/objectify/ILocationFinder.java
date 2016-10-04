package com.degloba.rent.cqrs.readmodel.objectify;

import com.degloba.rent.domain.persistence.nosql.googleDatastore.api.objectify.Location;
import com.degloba.rent.domain.persistence.nosql.googleDatastore.api.objectify.Product;

public interface ILocationFinder {

	Location findLocationByProduct(Product product);

}
