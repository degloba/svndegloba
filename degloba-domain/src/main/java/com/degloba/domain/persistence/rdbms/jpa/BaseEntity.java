package com.degloba.domain.persistence.rdbms.jpa;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

// JPA
import javax.persistence.*;

//
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.degloba.domain.ioc.InstanceFactory;
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
	   
	   
	   @Temporal(TemporalType.TIMESTAMP)
	   private Date expired;
	   
	   private boolean disabled;
	          
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
		

		   public static  <E extends BaseEntity> E get(Class<E> clazz, String id) {
		       return (E) getRepository().get(clazz, id);
		   }

		   public static <T extends BaseEntity> T getUnmodified(Class<T> clazz, T entity) {
		       return (T) getRepository().getUnmodified(clazz, entity);
		   }

		   public static <T extends BaseEntity> T load(Class<T> clazz, Serializable id) {
		       return (T) getRepository().load(clazz, id);
		   }

		   public static <E extends BaseEntity> List<E> findAll(Class<E> clazz) {
		       return getRepository().createCriteriaQuery(clazz).list();
		   }

		   public static <T extends BaseEntity> List<T> findByProperty(Class<T> clazz, String propName, Object value) {
		       return getRepository().findByProperty(clazz, propName, value);
		   }

		    public static <E extends BaseEntity> List<E> findByProperties(Class<E> clazz, Map<String, Object> propValues) {
		       return getRepository().findByProperties(clazz, NamedParameters.create(propValues));
		   }
		    
		    
		    /**
		     * åˆ¤æ–­å®žä½“æ˜¯å�¦å·²ç»�å¤±æ•ˆ
		     * @return å¦‚æžœå®žä½“å·²ç»�å¤±æ•ˆåˆ™è¿”å›žtrueï¼Œå�¦åˆ™è¿”å›žfalse
		     */
		    public boolean isDisabled() {
		        return disabled;
		    }
		    
		    
		    /**
		     * æ ¹æ�®å�•ä¸ªå±žæ€§å€¼ä»¥â€œå±žæ€§=å±žæ€§å€¼â€�çš„æ–¹å¼�æŸ¥æ‰¾ç¬¦å�ˆæ�¡ä»¶çš„å�•ä¸ªå®žä½“ï¼Œé€šå¸¸ç”¨äºŽæ ¹æ�®ä¸šåŠ¡ä¸»é”®æ‰¾åˆ°å”¯ä¸€å®žä½“
		     * @param <T> å®žä½“æ‰€å±žçš„ç±»åž‹
		     * @param clazz å®žä½“æ‰€å±žçš„ç±»
		     * @param propName å±žæ€§å��
		     * @param value åŒ¹é…�çš„å±žæ€§å€¼
		     * @return ç¬¦å�ˆæ�¡ä»¶çš„å®žä½“åˆ—è¡¨
		     */
		    public static <T extends BaseEntity> T getByProperty(Class<T> clazz, String propName, Object value) {
		        List<T> entities = findByProperty(clazz, propName, value);
		        return entities == null || entities.isEmpty() ? null : entities.get(0);
		    }
		    
		    /**
		     * ä½¿å®žä½“å¤±æ•ˆï¼Œå¯¹ç³»ç»Ÿæ�¥è¯´ï¼Œç­‰ä»·äºŽå®žä½“å·²ç»�åœ¨é€»è¾‘ä¸Šè¢«åˆ é™¤
		     */
		    public void disable(Date date) {
		        disabled = true;
		        expired = date;
		        save();
		    }


   
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