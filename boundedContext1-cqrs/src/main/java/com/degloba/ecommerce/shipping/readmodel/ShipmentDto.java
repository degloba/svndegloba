/*
 * Copyright 2011-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.degloba.ecommerce.shipping.readmodel;

import java.io.Serializable;


import com.google.appengine.api.datastore.Key;

//import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.AggregateId;
import com.degloba.ecommerce.shipping.domain.ShippingStatus;

@SuppressWarnings("serial")
public class ShipmentDto implements Serializable {

    private Key shipmentId;
    private Key orderId;
    private ShippingStatus status;

    public ShipmentDto(Key shipmentId, Key orderId, ShippingStatus status) {
        this.shipmentId = shipmentId;
        this.orderId = orderId;
        this.status = status;
    }

    public Key getShipmentId() {
        return shipmentId;
    }

    public Key getOrderId() {
        return orderId;
    }

    public ShippingStatus getStatus() {
        return status;
    }
}
