package com.degloba.rent.cqrs.readmodel.objectify;

import com.degloba.rent.domain.objectify.Location;
import com.degloba.rent.domain.objectify.Product;

public interface ILocationFinder {

	Location findLocationByProduct(Product product);

}
