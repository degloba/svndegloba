package com.degloba.ecommerce.shipping.cqrs.readmodel;

import java.util.List;

public interface ShipmentFinder {

    List<ShipmentDto> findShipment();

}
