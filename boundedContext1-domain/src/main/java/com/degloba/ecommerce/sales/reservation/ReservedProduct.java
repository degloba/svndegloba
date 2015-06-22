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
package com.degloba.ecommerce.sales.reservation;

import com.degloba.annotations.ValueObject;
//import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.AggregateId;
import com.degloba.domain.sharedkernel.Money;
import com.google.appengine.api.datastore.Key;

@ValueObject
public class ReservedProduct {

	private String name;
	
	private Money totalCost;
	
	private Key productId;

	private int quantity;
	
	public ReservedProduct(Key productId, String name, int quantity, Money totalCost) {
		this.productId = productId;
		this.name = name;
		this.quantity = quantity;
		this.totalCost = totalCost;
	}

	public String getName() {
		return name;
	}
	
	public Money getTotalCost() {
		return totalCost;
	}
	
	public Key getProductId() {
		return productId;
	}

	public int getQuantity() {
		return quantity;
	}
}
