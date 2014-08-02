package com.degloba.boundedContext.domain;

// JPA
import javax.jdo.PersistenceManager;
import javax.jdo.spi.StateManager;
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
	
	private Integer modalpanelid;
	private String descripcio;
	private String titol;
	
	/*@Embedded
	private ClientData clientData; */
	
	public Integer getModalpanelid() {
		return modalpanelid;
	}
	public void setModalpanelid(Integer modalpanelid) {
		this.modalpanelid = modalpanelid;
	}
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
	
	
	public Modalpanel() {}
	
	/*	public Modalpanel(AggregateId aggregateId) {
		this.aggregateId = aggregateId;
	}*/
	
	
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
	@Override
	public void jdoReplaceDetachedState() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void jdoCopyKeyFieldsFromObjectId(ObjectIdFieldConsumer arg0,
			Object arg1) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void jdoCopyKeyFieldsToObjectId(Object arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void jdoCopyKeyFieldsToObjectId(ObjectIdFieldSupplier arg0,
			Object arg1) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Object jdoGetObjectId() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public PersistenceManager jdoGetPersistenceManager() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object jdoGetTransactionalObjectId() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object jdoGetVersion() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean jdoIsDeleted() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean jdoIsDirty() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean jdoIsNew() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean jdoIsPersistent() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean jdoIsTransactional() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Object jdoNewObjectIdInstance() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object jdoNewObjectIdInstance(Object arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void jdoProvideFields(int[] arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void jdoReplaceFields(int[] arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void jdoReplaceFlags() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void jdoReplaceStateManager(StateManager arg0)
			throws SecurityException {
		// TODO Auto-generated method stub
		
	}
	
}
