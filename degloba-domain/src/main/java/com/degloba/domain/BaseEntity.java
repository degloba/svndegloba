package com.degloba.domain;

import java.util.Map;

// JPA
import javax.persistence.*;

//
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

// Dayatang Utils
import org.dayatang.utils.BeanUtils;

// Appengine
import com.google.appengine.api.datastore.Key;


/**
* Abstract entity class can be used as the base class for all areas of the entity.
*/

@MappedSuperclass
public abstract class BaseEntity implements com.degloba.domain.Entity {

   private static final long serialVersionUID = 8882145540383345037L;

	// ALWAYS ADD NEW STATUS AT THE END - because the entityStatus field is
   // annotated as ordinal in sake of performance
 /*  public static enum EntityStatus {
       ACTIVE, ARCHIVE
   }*/


   @Id  
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Key id;
   
   
   /*    @EmbeddedId
	@AttributeOverrides({
		  @AttributeOverride(name = "aggregateId", column = @Column(name = "aggregateId", nullable = false))})
	private AggregateId aggregateId; */
   

/*   @Enumerated(EnumType.ORDINAL)
   private EntityStatus entityStatus = EntityStatus.ACTIVE;

   public void markAsRemoved() {
       entityStatus = EntityStatus.ARCHIVE;
   }*/
   
   
   // getters - setters
   
   public Key getId() {
		return id;
	}

	public void setId(Key id) {
		this.id = id;
	}
 	
/*   public EntityStatus getEntityStatus() {
       return entityStatus;
   }*/
   

   /**
    * Determine whether the entity already exists in the database.
    * @return If the entity that owns the id of the database already exists returns true, otherwise false.
    */
   public boolean existed() {
       Object id = getId();
       if (id == null) {
           return false;
       }
       if (id instanceof Number && ((Number)id).intValue() == 0) {
           return false;
       }
       return getRepository().exists(getClass(), getId());
   }

   /**
    * Determines whether the entity does not exist in the database.
    * @return If the entity that owns the id is already in the database returns false, otherwise it returns true.
    */
   public boolean notExisted() {
       return !existed();
   }

   private static EntityRepository repository;

   /**
    * Get warehousing object instance. If you do not have a warehouse to get an instance of the IoC container through InstanceFactory.
    * @return Warehousing object instance
    */
   public static EntityRepository getRepository() {
       if (repository == null) {
           repository = InstanceFactory.getInstance(EntityRepository.class);
       }
       return repository;
   }

   /**
    * Set warehousing instance. This method is mainly used for unit testing. Product warehousing systems usually get through IoC container instance.
    * @param repository To set up an instance of an object storage
    */
   public static void setRepository(EntityRepository repository) {
       BaseEntity.repository = repository;
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