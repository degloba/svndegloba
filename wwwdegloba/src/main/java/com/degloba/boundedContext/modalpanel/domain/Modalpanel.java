package com.degloba.boundedContext.modalpanel.domain;

// JPA
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;





// Aggregate
import domain.support.BaseAggregateRoot;
import domain.annotations.AggregateRoot;
import domain.canonicalmodel.publishedlanguage.AggregateId;
import domain.canonicalmodel.publishedlanguage.ClientData;

@AggregateRoot
@Entity
public class Modalpanel extends BaseAggregateRoot{
	
	private String descripcio;
	private String titol;
	
	public Modalpanel() {}
	
/*	@EmbeddedId
	private AggregateId aggregateId;*/
	
	/*@Embedded
	private ClientData clientData; */
	
	public String getDescripcio() {
		return descripcio;
	}
	public void setDescripcio(String descripcio) {
		this.descripcio = descripcio;
	}
	public String getTitol() {
		return titol;
	}
	public void setTitol(String titol) {
		this.titol = titol;
	}
	
	
		
	public enum ModalpanelStatus{
		STANDARD, VIP, PLATINUM
	}

	@Enumerated(EnumType.STRING)
	private ModalpanelStatus status;


	public void changeStatus(ModalpanelStatus status){
		if (status.equals(this.status))
			return;

		this.status = status;

		//Sample Case: give 10% rebate for all draft orders - communication via events with different Bounded Context to achieve decoupling
		///////eventPublisher.publish(new CustomerStatusChangedEvent(getAggregateId(), status));
	}
	
}
