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
package com.degloba.ecommerce.sales.readmodel.orders;

import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.datastore.Key;

//import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.AggregateId;

public class OrderDto {

	private Key orderId;
	private List<OrderedProductDto> orderedProducts = new ArrayList<OrderedProductDto>();
	private OrderStatus status;
	private Boolean confirmable;

	public Key getOrderId() {
		return orderId;
	}

	public void setOrderId(Key orderId) {
		this.orderId = orderId;
	}

	public List<OrderedProductDto> getOrderedProducts() {
		return orderedProducts;
	}

	public void setOrderedProducts(List<OrderedProductDto> orderedProducts) {
		this.orderedProducts = orderedProducts;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public Boolean getConfirmable() {
		return confirmable;
	}

	public void setConfirmable(Boolean confirmable) {
		this.confirmable = confirmable;
	}
}