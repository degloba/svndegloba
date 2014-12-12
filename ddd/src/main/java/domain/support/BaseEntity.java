package domain.support;

// JPA

import domain.seedwork.IRepository;

import java.util.Map;

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


import javax.persistence.Transient;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.degloba.utils.BeanUtils;

import domain.canonicalmodel.publishedlanguage.AggregateId;


/**
 * @author degloba
 *
 */
@Entity
@MappedSuperclass
public abstract class BaseEntity implements domain.seedwork.Entity {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
	
	/**
     * 判断该实体是否已经存在于数据库中。
     * @return 如果数据库中已经存在拥有该id的实体则返回true，否则返回false。
     */
    @Override
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
     * 判断该实体是否不存在于数据库中。
     * @return 如果数据库中已经存在拥有该id的实体则返回false，否则返回true。
     */
    @Override
    public boolean notExisted() {
        return !existed();
    }

    private static IRepository<BaseAggregateRoot> repository;

    /**
     * 获取仓储对象实例。如果尚未拥有仓储实例则通过InstanceFactory向IoC容器获取一个。
     * @return 仓储对象实例
     */
    public static IRepository<BaseAggregateRoot> getRepository() {
        if (repository == null) {
            repository = InstanceFactory.getInstance(IRepository.class);
        }
        return repository;
    }

    /**
     * 设置仓储实例。该方法主要用于单元测试。产品系统中通常是通过IoC容器获取仓储实例。
     * @param repository 要设置的仓储对象实例
     */
    public static void setRepository(IRepository<BaseAggregateRoot> repository) {
        BaseEntity.repository = repository;
    }
    
    /**
     * 获取业务主键。业务主键是判断相同类型的两个实体在业务上的等价性的依据。如果相同类型的两个
     * 实体的业务主键相同，则认为两个实体是相同的，代表同一个实体。
     * <p>业务主键由实体的一个或多个属性组成。
     * @return 组成业务主键的属性的数组。
     */
    public String[] businessKeys() {
        return new String[] {};
    }

    /**
     * 依据业务主键获取哈希值。用于判定两个实体是否等价。
     * 等价的两个实体的hashCode相同，不等价的两个实体hashCode不同。
     * @return 实体的哈希值
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
     * 依据业务主键判断两个实体是否等价。
     * @param other 另一个实体
     * @return 如果本实体和other等价则返回true,否则返回false
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
