package com.degloba.ecommerce.vendes.facturacio.domain.persistence.rdbms.jpa;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import com.degloba.ecommerce.vendes.productes.domain.persistence.rdbms.jpa.ProducteData;
import com.degloba.persistence.domain.sharedkernel.Money;
import com.degloba.persistence.rdbms.api.jpa.BaseEntity;


/**
 * @author degloba
 *
 */
@Entity
public class LiniaFacturacio extends BaseEntity{
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	@Embedded
	private ProducteData product;
	
	private int quantitat;
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "denomination", column = @Column(name = "NET_DENOMINATION")),
		@AttributeOverride(name = "currencyCode", column = @Column(name = "NET_CURRENCYCODE")) })
	private Money net;
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "denomination", column = @Column(name = "gros_denomination")),
		@AttributeOverride(name = "currencyCode", column = @Column(name = "gros_currencyCode")) })
	private Money gros;
	
	@Embedded
	private Tax tax;

	/**
	 * JPA only
	 */
	public LiniaFacturacio(){}
	

	public LiniaFacturacio(ProducteData product, int quantitat, Money net, Tax tax) {
		this.product = product;
		this.quantitat = quantitat;
		this.net = net;
		this.tax = tax;
		
		this.gros = net.add(tax.getQuantitat());	
	}

	public ProducteData getProduct() {
		return product;
	}

	public int getQuantitat() {
		return quantitat;
	}

	public Money getNet() {
		return net;
	}

	public Money getGros() {
		return gros;
	}
	
	public Tax getTax(){
		return tax;
	}


	@Override
	public boolean existed() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean notExisted() {
		// TODO Auto-generated method stub
		return false;
	}


}
