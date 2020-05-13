package com.degloba.ecommerce.compres.domain.persistence.rdbms.jpa;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.degloba.domain.annotations.AggregateRoot;

import com.degloba.persistence.domain.sharedkernel.Money;
import com.degloba.persistence.rdbms.api.jpa.AggregateId;
import com.degloba.persistence.rdbms.api.jpa.BaseAggregateRoot;
import com.degloba.persistence.rdbms.api.jpa.ClientData;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * @category Compra
 * 
 * @author degloba
 *
 */
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@AggregateRoot
public class Compra extends BaseAggregateRoot{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
//	@Fetch(FetchMode.JOIN)
	@OrderColumn(name = "itemNumber")
	//@JoinColumn(name = "purchase_id")
/*    @JoinColumns(
    	    {@JoinColumn(name = "purchase_id", referencedColumnName = "aggregateId",
    	                 insertable = false, updatable = false)
    	     })*/
	private List<CompraArticle> items;
	
	private boolean pagada;

	@Embedded
	private ClientData clientData;

	@Temporal(TemporalType.DATE)
	private Date dataCompra;

	@Embedded
	private Money totalCost;

	
	public Compra(AggregateId aggregateId, ClientData clientData, List<CompraArticle> items, Date dataCompra,
			boolean paid, Money totalCost){
		this.aggregateId = aggregateId;
		this.clientData = clientData;
		this.items = items;
		this.dataCompra = dataCompra;
		this.pagada = pagada;
		this.totalCost = totalCost;
	}
	
	public Compra(AggregateId aggregateId) {
		// TODO Auto-generated constructor stub
		this.aggregateId = aggregateId;
	}

	public void confirm() {
		pagada = true;
				
		//eventPublisher.publish(new ComandaEnviadaEvent(getAggregateId()));
	}

	
	public Collection<CompraArticle> getItems() {
		return (Collection<CompraArticle>) Collections.unmodifiableCollection(items);
	}


}
