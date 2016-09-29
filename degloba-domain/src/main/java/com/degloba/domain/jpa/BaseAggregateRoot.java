package com.degloba.domain.jpa;


import java.util.Date;
import java.util.Map;



// CDI Java EE 6
import javax.inject.Inject;

// JPA
import javax.persistence.Entity;
import javax.persistence.Version;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;



// Spring
import org.springframework.context.annotation.Scope;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.degloba.domain.sharedkernel.exceptions.DomainOperationException;
import com.degloba.event.domain.IDomainEvent;
import com.degloba.event.domain.IDomainEventPublisher;
import com.degloba.utils.BeanUtils;
import com.google.appengine.api.datastore.Key;


/**
 * @author degloba
 * 
 * @category Defineix la Entitat Base del Domini AggregateRoot i per tant lligada al Domini
 * Lligat al Domini.Totes les entitats del Domini hereden d'ella
 * 
 */

	/////////////@Component
	@Scope("prototype")//created in domain factories, not in spring container, therefore we don't want eager creation
	@Entity
	@MappedSuperclass
	public abstract class BaseAggregateRoot extends com.degloba.domain.jpa.BaseEntity {

		private static final long serialVersionUID = 1L;
		   
		/**
		 * control de concurrencia
		 */
		@Version
		private Long version;
				
		private Boolean actiu; //esborrat logic
		
		private Date DataVigenciaIni;
		private Date DataVigenciaFi;
		private Date DataIniciSeleccio;
		

		@Transient
		@Inject
		protected IDomainEventPublisher<IDomainEvent<Object>> eventPublisher;
		
		public void markAsRemoved() {
			//aggregateStatus = AggregateStatus.ARCHIVE;
		}
		
		public Key getAggregateId() {
			return getId();
		}

		public boolean isRemoved() {
			return true;
			////////////////return aggregateStatus == AggregateStatus.ARCHIVE;
		}
		
		protected void domainError(String message) {
			throw new DomainOperationException(getAggregateId(), message);
		}
		
	
	/*	protected void domainError(String message) { 
					throw new DomainOperationException(aggregateId, message); 
				} */


    // getters - setters
	
	public Boolean getActiu() {
		return actiu;
	}

	public void setActiu(Boolean actiu) {
		this.actiu = actiu;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Date getDataVigenciaIni() {
		return DataVigenciaIni;
	}

	public void setDataVigenciaIni(Date dataVigenciaIni) {
		DataVigenciaIni = dataVigenciaIni;
	}

	public Date getDataVigenciaFi() {
		return DataVigenciaFi;
	}

	public void setDataVigenciaFi(Date dataVigenciaFi) {
		DataVigenciaFi = dataVigenciaFi;
	}

	public Date getDataIniciSeleccio() {
		return DataIniciSeleccio;
	}

	public void setDataIniciSeleccio(Date dataIniciSeleccio) {
		DataIniciSeleccio = dataIniciSeleccio;
	}

	
	
	 /**
     * Sample of Domain Event usage<br>
     * Event Publisher is injected by Factory/Repo 
     
    @Transient
    protected DomainEventPublisher eventPublisher; */

   
	/**
     * Sample technique of injecting Event Publisher into the Aggregate.<br>
     * <br>
     * Can be called only once by Factory/Repository<br>
     * Visible for package (Factory/Repository)
     */
   public void setDomainEventPublisher(IDomainEventPublisher<IDomainEvent<Object>> domainEventPublisher) {
        if (this.eventPublisher != null)
            throw new IllegalStateException("Publisher is already set! Probably You have logical error in code");
        this.eventPublisher = domainEventPublisher;
    }

	public IDomainEventPublisher<IDomainEvent<Object>> getDomainEventPublisher() {
		return eventPublisher;
	}


	    public String[] businessKeys() {
	        return new String[] {};
	    }


	    @Override
	    public int hashCode() {
	        HashCodeBuilder builder = new HashCodeBuilder(13, 37);
	        Map<String, Object> propValues = new BeanUtils(this).getPropValues();
	        
	        for (String businessKey : businessKeys()) {
	            builder = builder.append(propValues.get(businessKey));
	        }
	        return builder.toHashCode();
	    }

	
	    @Override
	    public boolean equals(Object other) {
	        if (this == other) {
	            return true;
	        }
	        if (other == null) {
	            return false;
	        }
	        if (businessKeys() == null || businessKeys().length == 0) {
	            return false;
	        }
	        if (!(this.getClass().isAssignableFrom(other.getClass()))) {
	            return false;
	        }
	        Map<String, Object> thisPropValues = new BeanUtils(this).getPropValuesExclude(Transient.class);
	        Map<String, Object> otherPropValues = new BeanUtils(other).getPropValuesExclude(Transient.class);
	        EqualsBuilder builder = new EqualsBuilder();
	        for (String businessKey : businessKeys()) {
	            builder.append(thisPropValues.get(businessKey), otherPropValues.get(businessKey));
	        }
	        return builder.isEquals();
	    }
	    
	   
}
