package com.degloba.ecommerce.shipping.domain;

import com.degloba.domain.annotations.DomainRepository;
//import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.AggregateId;
import com.google.appengine.api.datastore.Key;

/**
 * @author degloba
 */
@DomainRepository
public interface IShipmentRepository {

    void save(Shipment order);

    Shipment load(Key orderId);
}
