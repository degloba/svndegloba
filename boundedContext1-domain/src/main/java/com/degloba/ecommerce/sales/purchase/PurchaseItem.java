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
package com.degloba.ecommerce.sales.purchase;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;

import com.degloba.annotations.ValueObject;
import com.degloba.domain.BaseEntity;
import com.degloba.ecommerce.sales.productscatalog.ProductData;
import com.degloba.domain.sharedkernel.Money;

/**
 * Models purchased items - contains copied data in case on catalog proces and discount change 
 * @author Slawek
 *
 */
@ValueObject
@Entity
public class PurchaseItem extends BaseEntity{
	
	@Embedded
	private ProductData productData;
	
	private int quantity;	
	
	@AttributeOverrides({
		@AttributeOverride(name = "denomination", column = @Column(name = "purchaseTotalCost_denomination")),
		@AttributeOverride(name = "currencyCode", column = @Column(name = "purchaseTotalCost_currencyCode")) })
	private Money totalCost;
	
	@SuppressWarnings("unused")
	private PurchaseItem() {}
	
	public PurchaseItem(ProductData productData, int quantity, Money totalCost) {
		this.productData = productData;
		this.quantity = quantity;
		this.totalCost = totalCost;
	}

	public int getQuantity() {
		return quantity;
	}

	public ProductData getProductData() {
		return productData;
	}

	public Money getTotalCost() {
		return totalCost;
	}

	
}
