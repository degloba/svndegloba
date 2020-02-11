package com.degloba.ecommerce.enviaments.domain.persistence.rdbms.jpa;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.degloba.domain.annotations.AggregateRoot;
import com.degloba.ecommerce.enviaments.domain.enums.EstatEnviament;
import com.degloba.ecommerce.enviaments.domain.events.EnviamentLliuratEvent;
import com.degloba.persistence.rdbms.jpa.AggregateId;
import com.degloba.persistence.rdbms.jpa.BaseAggregateRoot;
import com.degloba.persistence.rdbms.jpa.BaseEntity;
import com.degloba.persistence.rdbms.jpa.BaseEntity;


/**
 * @category Entitat de persistencia JPA d'un enviament
 * 
 * @author degloba
 */
@Entity
@AggregateRoot
public class Enviament extends BaseAggregateRoot {

	private static final long serialVersionUID = 1L;
	
	
	@AttributeOverrides({
		@AttributeOverride(name = "aggregateId", column = @Column(name = "comandaId"))})  
    private AggregateId comandaId;
	
    private EstatEnviament estatEnviament;

    
    private Enviament() {}

    public Enviament(AggregateId enviamentId, AggregateId comandaId) {
        this.aggregateId = enviamentId;
    	this.comandaId = comandaId;
        this.estatEnviament = EstatEnviament.WAITING;
    }

    /**
     * L'entrega ha estat enviada al client.
     */
    public void envia() {
        if (estatEnviament != EstatEnviament.WAITING) {
            throw new IllegalStateException("cannot ship in status " + estatEnviament);
        }
        estatEnviament = EstatEnviament.SENT;
  ////////eventPublisher.publish(new OrderShippedEvent(comandaId, getAggregateId()));
    }

    /**
     * L'entrega ha estat confirmada com a rebuda pel client.
     */
    public void entregar() {
        if (estatEnviament != EstatEnviament.SENT) {
            throw new IllegalStateException("cannot deliver in status " + estatEnviament);
        }
        estatEnviament = EstatEnviament.DELIVERED;
        
       // eventPublisher.publish(new EnviamentLliuratEvent(getAggregateId()));
    }

    public AggregateId getComandaId() {    	
    	return comandaId;
    }


}
