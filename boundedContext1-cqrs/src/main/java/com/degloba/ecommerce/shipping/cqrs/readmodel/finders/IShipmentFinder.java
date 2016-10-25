package com.degloba.ecommerce.shipping.cqrs.readmodel;

import java.util.List;

import com.degloba.cqrs.query.annotations.Finder;

@Finder
public interface IShipmentFinder {

    List<ShipmentDto> findShipment();

}
