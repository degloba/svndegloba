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
package com.degloba.ecommerce.sales.api.service;

//import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.AggregateId;
import com.google.appengine.api.datastore.Key;

import com.degloba.ecommerce.sales.api.command.OrderDetailsCommand;
import com.degloba.ecommerce.sales.offer.Offer;


public interface OrderingService {
	// 1.
	public Key createOrder();

	// 2.
	public void addProduct(Key orderId, Key productId, int quantity);
	
	// 3. 
	public Offer calculateOffer(Key orderId);

	// 4.
	public void confirm(Key orderId, OrderDetailsCommand orderDetailsCommand, Offer seenOffer)
			throws OfferChangedExcpetion;
}
