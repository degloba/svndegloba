package com.degloba.domain.persistence.rdbms.jpa;


import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;


// CDI Java EE 6
import javax.inject.Inject;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.EmbeddedId;
// JPA

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Version;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Column;



// Spring
import org.springframework.context.annotation.Scope;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.degloba.domain.event.IDomainEventBus;
import com.degloba.domain.ioc.InstanceFactory;
import com.degloba.domain.persistence.rdbms.jpa.canonicalmodel.publishedlanguage.AggregateId;
import com.degloba.domain.sharedkernel.exceptions.DomainOperationException;
import com.degloba.event.domain.IDomainEvent;
import com.degloba.event.domain.IDomainEventPublisher;
import com.degloba.utils.BeanUtils;



/**
 * @author degloba
 * 
 * @category Defineix la Entitat Base del Domini AggregateRoot i per tant lligada al Domini
 * Lligat al Domini.Totes les entitats del Domini hereden d'ella
 * 
 */

	/////////////@Component
	@Scope("prototype")//created in domain factories, not in spring container, therefore we don't want eager creation
	@MappedSuperclass
	public abstract class BaseAggregateRoot {  // extends BaseEntity{   

		private static final long serialVersionUID = 1L;
		   
		
		public static enum AggregateStatus {
			ACTIVE, ARCHIVE
		}
		
		private boolean disabled;
		
		@EmbeddedId		
		@AttributeOverrides({
			  @AttributeOverride(name = "aggregateId", column = @Column(name = "aggregateId", nullable = false))})  
		protected AggregateId aggregateId;
		
		
		/**
		 * control de concurrencia
		 */
		@Version
		private Long version;
		
		@Enumerated(EnumType.ORDINAL)
		private AggregateStatus aggregateStatus = AggregateStatus.ACTIVE;
				
		
		/*
		 * Domain Publisher (Opción 1)
		 */
		@Transient
		@Inject
		protected IDomainEventPublisher<IDomainEvent<Object>> eventPublisher;
		
		
		/*
		 * Domain Publisher (Opción 2)
		 */
		@Transient
		@Inject
		protected IDomainEventBus domainEventBus;
				
				
		private Boolean actiu; //esborrat logic
		
		@Temporal(TemporalType.TIMESTAMP)
		private Date expired;
		   
		@Temporal(TemporalType.DATE)
		private Date DataVigenciaIni;
		
		@Temporal(TemporalType.DATE)
		private Date DataVigenciaFi;
		
		
		public void markAsRemoved() {
			aggregateStatus = AggregateStatus.ARCHIVE;
		}
		
		
		public boolean isRemoved() {
			return aggregateStatus == AggregateStatus.ARCHIVE;
		}
		
		protected void domainError(String message) {
			throw new DomainOperationException(getAggregateId(), message);
		}
			

		// getters - setters
	
		public AggregateId getAggregateId() {
			return aggregateId;
		}

		public void setAggregateId(AggregateId aggregateId) {
			this.aggregateId = aggregateId;
		}

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
	
		
	  
		public AggregateStatus getAggregateStatus() {
			return aggregateStatus;
		}

		public void setAggregateStatus(AggregateStatus aggregateStatus) {
			this.aggregateStatus = aggregateStatus;
		}
	
		public IDomainEventPublisher<IDomainEvent<Object>> getEventPublisher() {
			return eventPublisher;
		}

		public void setEventPublisher(IDomainEventPublisher<IDomainEvent<Object>> eventPublisher) {
			this.eventPublisher = eventPublisher;
		}


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
		
		
		public IDomainEventBus getDomainEventBus() {
			return domainEventBus;
		}

	
		public void setDomainEventBus(IDomainEventBus domainEventBus) {
			this.domainEventBus = domainEventBus;
		}


	/**
	    * Get Natural key. Natural key is to determine the two entities of the same type on the basis of operational equivalence. If the same type of two
	    * Business entities the same primary key, then that the two entities are identical, represent the same entity.
	    * Natural key by one or more entities, attributes.
	    * @return Consisting of an array of attributes of Natural key.
	    */
	    public String[] businessKeys() {
	        return new String[] {};
	    }


	    /**
	     * Gets a hash value based Natural key. Used to determine whether two entities equivalent.
	     * The equivalent of two different hashCode same entity, the two entities are not equivalent hashCode.
	     * @return Hashes entities
	     */
	    @Override
	    public int hashCode() {
	        HashCodeBuilder builder = new HashCodeBuilder(13, 37);
	        Map<String, Object> propValues = new BeanUtils(this).getPropValues();
	        
	        for (String businessKey : businessKeys()) {
	            builder = builder.append(propValues.get(businessKey));
	        }
	        return builder.toHashCode();
	    }

	
	    /**
	     * Natural key judgments based on two entities are equal.
	     * @param other Another entity
	     * @return If this is the equivalent entities and other returns true, otherwise false
	     */
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
	    
	    
		public BaseAggregateRoot() {
			super();
						
			// Inicializacion
			//this.setAggregateId(AggregateId.generate());
			this.DataVigenciaIni = Calendar.getInstance().getTime();
			this.aggregateStatus = AggregateStatus.ACTIVE;
			
		}

		
		 public boolean isDisabled() {
		        return disabled;
		    }
		    

	
		    
		    public void disable(Date date) {
		        disabled = true;
		        expired = date;
		        save();
		    }
	   
		
		public static  <E extends BaseAggregateRoot> E get(Class<E> clazz, String id) {
		       return (E) getRepository().get(clazz, id);
		   }

		   public static <T extends BaseAggregateRoot> T getUnmodified(Class<T> clazz, T entity) {
		       return (T) getRepository().getUnmodified(clazz, entity);
		   }

		   public static <T extends BaseAggregateRoot> T load(Class<T> clazz, Serializable id) {
		       return (T) getRepository().load(clazz, id);
		   }

		   public static <E extends BaseAggregateRoot> List<E> findAll(Class<E> clazz) {
		       return getRepository().createCriteriaQuery(clazz).list();
		   }

		   public static <T extends BaseAggregateRoot> List<T> findByProperty(Class<T> clazz, String propName, Object value) {
		       return getRepository().findByProperty(clazz, propName, value);
		   }

		    public static <E extends BaseAggregateRoot> List<E> findByProperties(Class<E> clazz, Map<String, Object> propValues) {
		       return getRepository().findByProperties(clazz, NamedParameters.create(propValues));
		   }
		    
		    public static <T extends BaseAggregateRoot> T getByProperty(Class<T> clazz, String propName, Object value) {
		        List<T> entities = findByProperty(clazz, propName, value);
		        return entities == null || entities.isEmpty() ? null : entities.get(0);
		    }
		    
		    /**
		     * Determine whether the entity already exists in the database.
		     * @return If the entity that owns the id of the database already exists returns true, otherwise false.
		     */
		    public boolean existed() {
		        Object id = getAggregateId();
		        if (id == null) {
		            return false;
		        }
		        if (id instanceof Number && ((Number)id).intValue() == 0) {
		            return false;
		        }
		        return getRepository().exists(getClass(), getAggregateId());
		    }

		    /**
		     * Determines whether the entity does not exist in the database.
		     * @return If the entity that owns the id is already in the database returns false, otherwise it returns true.
		     */
		    public boolean notExisted() {
		        return !existed();
		    }
		    
		    private static IEntityRepository repository;

		    /**
		     * Get warehousing object instance. If you do not have a warehouse to get an instance of the IoC container through InstanceFactory.
		     * @return Warehousing object instance
		     */
		    public static IEntityRepository getRepository() {
		        if (repository == null) {
		            repository = InstanceFactory.getInstance(IEntityRepository.class);
		        }
		        return repository;
		    }

		    /**
		     * Set warehousing instance. This method is mainly used for unit testing. Product warehousing systems usually get through IoC container instance.
		     * @param repository To set up an instance of an object storage
		     */
		    public static void setRepository(IEntityRepository repository) {
		        BaseAggregateRoot.repository = repository;
		    }
		    
		    
		    /**
		     * The entity itself persisted to the database
		     */
		    public void save() {
		        getRepository().save(this);
		    }

		    /**
		     * Entity itself will be deleted from the database
		     */
		    public void remove() {
		        getRepository().remove(this);
		    }
		    
}
