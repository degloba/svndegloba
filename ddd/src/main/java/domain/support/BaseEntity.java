package domain.support;

// JPA

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
//import javax.persistence.Version;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import domain.canonicalmodel.publishedlanguage.AggregateId;


/**
 * @author degloba
 *
 */
@Entity
@MappedSuperclass
public abstract class BaseEntity{

    // ALWAYS ADD NEW STATUS AT THE END - because the entityStatus field is
    // annotated as ordinal in sake of performance
    public static enum EntityStatus {
        ACTIVE, ARCHIVE
    }

    //entityId because ID can mean something (some domain concept) in some Bounded Context
    @Id
    @GeneratedValue
    private Long aggregateId;
    
/*    @EmbeddedId
	@AttributeOverrides({
		  @AttributeOverride(name = "aggregateId", column = @Column(name = "aggregateId", nullable = false))})
	private AggregateId aggregateId; */


/*    @Version
    private Long version;*/

    @Enumerated(EnumType.ORDINAL)
    private EntityStatus entityStatus = EntityStatus.ACTIVE;

    public void markAsRemoved() {
        entityStatus = EntityStatus.ARCHIVE;
    }
    
    
    /**
     * Check if this  entity is transient,ie, without identity at this moment
     * @return  True if entity is transient, else false
     */
    public  Boolean IsTransient() {
    	//return this.entityId ==  0;  // guid.empty
    	return this.aggregateId == null;
    }

    
 /* (non-Javadoc)
 * @see domain.support.Entitat#Equals(java.lang.Object)
 */
  public Boolean Equals(Object obj)
  {	
	  if (this == obj)
			return true;
	  if (obj == null)
			return false;
	  if (obj == null || !(obj instanceof BaseEntity))
		  return false;

	  BaseEntity item = (BaseEntity)obj;
	  
/*	  if (item.entityId == null)
			return false;*/
	  if (item.aggregateId == null)
		return false;
		

	  if (item.IsTransient() || this.IsTransient())
		  return false;
	  else
		  //return item.entityId.equals(this.entityId);
		  return item.aggregateId.equals(this.aggregateId);
  
  }


private Integer _requestedHashCode;

	public int GetHashCode()
	{
		//return this.entityId.hashCode();
		return this.aggregateId.hashCode();
		/*if (!IsTransient())
			{
				if (!_requestedHashCode !=  null)
					_requestedHashCode = this.entityId.GetHashCode() ^ 31;
				return _requestedHashCode;
			}
		else
			return base.GetHashCode();*/
	}

    
    //    getters - setters
    
    /*private void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

	public Long getEntityId() {
        return entityId;
    }*/

	
    public EntityStatus getEntityStatus() {
        return entityStatus;
    }
    
	public Long getAggregateId() {
		return aggregateId;
	}

	public void setAggregateId(Long aggregateId) {
		this.aggregateId = aggregateId;
	}

    
    
}
