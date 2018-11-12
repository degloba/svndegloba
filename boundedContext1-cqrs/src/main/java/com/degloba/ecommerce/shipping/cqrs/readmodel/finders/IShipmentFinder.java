package com.degloba.ecommerce.shipping.cqrs.readmodel.finders;

import java.util.List;

import com.degloba.cqrs.query.annotations.Finder;
import com.degloba.ecommerce.shipping.cqrs.readmodel.dtos.ShipmentDto;


@Finder
public interface IShipmentFinder {

    List<ShipmentDto> findShipment();

}
