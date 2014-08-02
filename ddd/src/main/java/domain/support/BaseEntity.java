package domain.support;

// JPA
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
//import javax.persistence.Version;


/**
 * @author degloba
 *
 */
@MappedSuperclass
public abstract class BaseEntity extends Entitat{

    // ALWAYS ADD NEW STATUS AT THE END - because the entityStatus field is
    // annotated as ordinal in sake of performance
    public static enum EntityStatus {
        ACTIVE, ARCHIVE
    }

    //entityId because ID can mean something (some domain concept) in some Bounded Context
    @Id
    @GeneratedValue
    private Long entityId;


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
    public Boolean IsTransient() {
    	return this.entityId ==  0;  // guid.empty
    }

    
 /* (non-Javadoc)
 * @see domain.support.Entitat#Equals(java.lang.Object)
 */
@Override
  public Boolean Equals(Object obj)
  {	
	  if (this == obj)
			return true;
	  if (obj == null)
			return false;
	  if (obj == null || !(obj instanceof BaseEntity))
		  return false;

	  BaseEntity item = (BaseEntity)obj;
	  
	  if (item.entityId == null)
			return false;
		

	  if (item.IsTransient() || this.IsTransient())
		  return false;
	  else
		  return item.entityId.equals(this.entityId);
  
  }


private Integer _requestedHashCode;

	public int GetHashCode()
	{
		return this.entityId.hashCode();
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
    
    private void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

	public Long getEntityId() {
        return entityId;
    }

    public EntityStatus getEntityStatus() {
        return entityStatus;
    }

	private void setEntityStatus(EntityStatus entityStatus) {
		this.entityStatus = entityStatus;
	}
    
    
}
