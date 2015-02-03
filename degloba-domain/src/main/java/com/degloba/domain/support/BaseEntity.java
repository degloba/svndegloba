package com.degloba.domain.support;

// JPA

import com.degloba.domain.InstanceFactory;
import com.degloba.domain.seedwork.IRepository;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
//import javax.persistence.Version;

import javax.persistence.Transient;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.degloba.utils.BeanUtils;


/**
 * @author degloba
 *
 */
@Entity
@MappedSuperclass
public abstract class BaseEntity extends com.degloba.domain.seedwork.Entity {
//public class BaseEntity implements domain.seedwork.IEntity {

	private static final long serialVersionUID = 1L;

	// ALWAYS ADD NEW STATUS AT THE END - because the entityStatus field is
    // annotated as ordinal in sake of performance
    public static enum EntityStatus {
        ACTIVE, ARCHIVE
    }

    //entityId because ID can mean something (some domain concept) in some Bounded Context
    @Id
    @GeneratedValue
    protected Long aggregateId;
    
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
    
    
    //    getters - setters
    
	
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

    private static IRepository repository;

    /**
     * Obtiene la instancia del objeto. 
     * Si usted no tiene un almacén para obtener una instancia del contenedor IoC través InstanceFactory .
     * @return 仓储对象实例
     */
    public static IRepository getRepository() {
        if (repository == null) {
            repository = InstanceFactory.getInstance(IRepository.class);
        }
        return repository;
    }

    /**
     * 设置仓储实例。该方法主要用于单元测试。产品系统中通常是通过IoC容器获取仓储实例。
     * @param repository 要设置的仓储对象实例
     */
    public static void setRepository(IRepository repository) {
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


	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Boolean IsTransient() {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public int GetHashCode() {
		// TODO Auto-generated method stub
		return 0;
	}
   
    
}
