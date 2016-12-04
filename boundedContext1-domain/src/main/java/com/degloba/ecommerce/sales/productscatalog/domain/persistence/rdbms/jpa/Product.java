package com.degloba.ecommerce.sales.productscatalog.domain.persistence.rdbms.jpa;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.degloba.domain.annotations.AggregateRoot;

import com.degloba.domain.persistence.rdbms.jpa.BaseAggregateRoot;
import com.degloba.domain.persistence.rdbms.jpa.canonicalmodel.publishedlanguage.AggregateId;
//import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.AggregateId;
import com.degloba.domain.sharedkernel.Money;


@Entity
@AggregateRoot
public class Product extends BaseAggregateRoot{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	@Embedded
	private Money price;
	
	private String name;
	
	@Enumerated(EnumType.STRING)
	private ProductType productType;
	
	@SuppressWarnings("unused")
	private Product(){}
	
	Product(AggregateId aggregateId, Money price, String name, ProductType productType){
		this.aggregateId = aggregateId;
		this.price = price;
		this.name = name;
		this.productType = productType;
	}
	
	public boolean isAvailabe(){		
		return ! isRemoved();//TODO explore domain rules
	}
	
	public Money getPrice() {
		return price;
	}
	
	public String getName() {
		return name;
	}
	
	public ProductType getProductType() {
		return productType;
	}
	
	public ProductData generateSnapshot(){
		return new ProductData(getAggregateId(), price, name, productType, new Date());
	}
	
}
