package domain.support;

import java.io.Serializable;
import java.util.Date;



// CDI Java EE 6
import javax.inject.Inject;
import javax.persistence.Entity;
// JPA
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;



// SPRING
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import domain.canonicalmodel.publishedlanguage.AggregateId;
import domain.sharedkernel.exceptions.DomainOperationException;

import java.util.Map;

import javax.persistence.*;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.degloba.utils.BeanUtils;

/**
 * @author degloba
 * 
 * @category Defineix la Entitat Base del Domini AggregateRoot i per tant lligada al Domini
 * Lligat al Domini.Totes les entitats del Domini hereden d'ella
 * 
 */

	@Component
	@Scope("prototype")//created in domain factories, not in spring container, therefore we don't want eager creation
	@Entity
	@MappedSuperclass
	//public class BaseAggregateRoot {  //extends BaseEntity {
	public abstract class BaseAggregateRoot {
		 
		
/*		@EmbeddedId
		@AttributeOverrides({
			  @AttributeOverride(name = "aggregateId", column = @Column(name = "aggregateId", nullable = false))})
		private AggregateId aggregateId; */
		
		@Id
		@GeneratedValue(strategy= GenerationType.IDENTITY)
		private Long aggregateId;

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
		protected IDomainEventPublisher<IDomainEvent<Object>> domainEventPublisher;
		
		
/*		public AggregateId getAggregateId() {
			return aggregateId;
		}

		public boolean isRemoved() {
			return aggregateStatus == AggregateStatus.ARCHIVE;
		}
		
		protected void domainError(String message) {
			throw new DomainOperationException(aggregateId, message);
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (obj instanceof BaseAggregateRoot) {
				BaseAggregateRoot other = (BaseAggregateRoot) obj;
				if (other.aggregateId == null)
					return false;
				return other.aggregateId.equals(aggregateId);
			}
			
			return false;
		}
		
		@Override
		public int hashCode() {	
			return aggregateId.hashCode();
		}	*/	
		
	
	/*	protected void domainError(String message) { 
					throw new DomainOperationException(aggregateId, message); 
				} */


    //    getters - setters
	
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
        if (this.domainEventPublisher != null)
            throw new IllegalStateException("Publisher is already set! Probably You have logical error in code");
        this.domainEventPublisher = domainEventPublisher;
    }

	public IDomainEventPublisher<IDomainEvent<Object>> getDomainEventPublisher() {
		return domainEventPublisher;
	}

	

	public Serializable getId() {
		// TODO Auto-generated method stub
		return this.getAggregateId();
	}

	public Long getAggregateId() {
		return aggregateId;
	}

	public void setAggregateId(Long aggregateId) {
		this.aggregateId = aggregateId;
	}



	    public boolean existed() {
	        Object id = getId();
	        if (id == null) {
	            return false;
	        }
	        if (id instanceof Number && ((Number)id).intValue() == 0) {
	            return false;
	        }
	        //return getRepository().exists(getClass(), getId());
	        return false;
	    }

	    /**
	     * åˆ¤æ–­è¯¥å®žä½“æ˜¯å�¦ä¸�å­˜åœ¨äºŽæ•°æ�®åº“ä¸­ã€‚
	     * @return å¦‚æžœæ•°æ�®åº“ä¸­å·²ç»�å­˜åœ¨æ‹¥æœ‰è¯¥idçš„å®žä½“åˆ™è¿”å›žfalseï¼Œå�¦åˆ™è¿”å›žtrueã€‚
	     */

	    public boolean notExisted() {
	        return !existed();
	    }

/*	    private static EntityRepository repository;

	    *//**
	     * èŽ·å�–ä»“å‚¨å¯¹è±¡å®žä¾‹ã€‚å¦‚æžœå°šæœªæ‹¥æœ‰ä»“å‚¨å®žä¾‹åˆ™é€šè¿‡InstanceFactoryå�‘IoCå®¹å™¨èŽ·å�–ä¸€ä¸ªã€‚
	     * @return ä»“å‚¨å¯¹è±¡å®žä¾‹
	     *//*
	    public static EntityRepository getRepository() {
	        if (repository == null) {
	            repository = InstanceFactory.getInstance(EntityRepository.class);
	        }
	        return repository;
	    }

	    *//**
	     * è®¾ç½®ä»“å‚¨å®žä¾‹ã€‚è¯¥æ–¹æ³•ä¸»è¦�ç”¨äºŽå�•å…ƒæµ‹è¯•ã€‚äº§å“�ç³»ç»Ÿä¸­é€šå¸¸æ˜¯é€šè¿‡IoCå®¹å™¨èŽ·å�–ä»“å‚¨å®žä¾‹ã€‚
	     * @param repository è¦�è®¾ç½®çš„ä»“å‚¨å¯¹è±¡å®žä¾‹
	     *//*
	    public static void setRepository(EntityRepository repository) {
	        BaseEntity.repository = repository;
	    }*/
	    
	    /**
	     * èŽ·å�–ä¸šåŠ¡ä¸»é”®ã€‚ä¸šåŠ¡ä¸»é”®æ˜¯åˆ¤æ–­ç›¸å�Œç±»åž‹çš„ä¸¤ä¸ªå®žä½“åœ¨ä¸šåŠ¡ä¸Šçš„ç­‰ä»·æ€§çš„ä¾�æ�®ã€‚å¦‚æžœç›¸å�Œç±»åž‹çš„ä¸¤ä¸ª
	     * å®žä½“çš„ä¸šåŠ¡ä¸»é”®ç›¸å�Œï¼Œåˆ™è®¤ä¸ºä¸¤ä¸ªå®žä½“æ˜¯ç›¸å�Œçš„ï¼Œä»£è¡¨å�Œä¸€ä¸ªå®žä½“ã€‚
	     * <p>ä¸šåŠ¡ä¸»é”®ç”±å®žä½“çš„ä¸€ä¸ªæˆ–å¤šä¸ªå±žæ€§ç»„æˆ�ã€‚
	     * @return ç»„æˆ�ä¸šåŠ¡ä¸»é”®çš„å±žæ€§çš„æ•°ç»„ã€‚
	     */
	    public String[] businessKeys() {
	        return new String[] {};
	    }

	    /**
	     * ä¾�æ�®ä¸šåŠ¡ä¸»é”®èŽ·å�–å“ˆå¸Œå€¼ã€‚ç”¨äºŽåˆ¤å®šä¸¤ä¸ªå®žä½“æ˜¯å�¦ç­‰ä»·ã€‚
	     * ç­‰ä»·çš„ä¸¤ä¸ªå®žä½“çš„hashCodeç›¸å�Œï¼Œä¸�ç­‰ä»·çš„ä¸¤ä¸ªå®žä½“hashCodeä¸�å�Œã€‚
	     * @return å®žä½“çš„å“ˆå¸Œå€¼
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
	     * ä¾�æ�®ä¸šåŠ¡ä¸»é”®åˆ¤æ–­ä¸¤ä¸ªå®žä½“æ˜¯å�¦ç­‰ä»·ã€‚
	     * @param other å�¦ä¸€ä¸ªå®žä½“
	     * @return å¦‚æžœæœ¬å®žä½“å’Œotherç­‰ä»·åˆ™è¿”å›žtrue,å�¦åˆ™è¿”å›žfalse
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
