package com.degloba.persistence.rdbms.api.jpa;


import java.util.Map;

// JPA
import javax.persistence.*;

//
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.degloba.utils.BeanUtils;



/**
* Abstract entity class can be used as the base class for all areas of the entity.
*/

@MappedSuperclass
public abstract class BaseEntity implements IEntity {

	private static final long serialVersionUID = 8882145540383345037L;

	public static final String FIND_ALL = null;
	
	public static final String TOTAL_RESULT = null;

	// ALWAYS ADD NEW STATUS AT THE END - because the entityStatus field is
   // annotated as ordinal in sake of performance
	 /*  public static enum EntityStatus {
	       ACTIVE, ARCHIVE
	   }*/


	   @Id  
	   @GeneratedValue(strategy = GenerationType.IDENTITY)  
	   private Long id;
	   
	   
	   @Version
	   @Column(name = "version")
	   private int version;
	   
	   	          
	   // getters - setters
	   
		public void setId(Long id) {
			this.id = id;
		}
	 	
		public Long getId() {
			return id;
		}
		
		   public int getVersion() {
		       return version;
		   }

		public void setVersion(int version) {
		       this.version = version;
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

   
  



}