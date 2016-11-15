package com.degloba.domain.persistence.rdbms.jpa;

// JPA
import javax.persistence.*;

import com.degloba.domain.persistence.rdbms.jpa.NamedParameters;
import com.degloba.domain.persistence.rdbms.jpa.canonicalmodel.publishedlanguage.AggregateId;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
* @author degloba
*/
@MappedSuperclass
public abstract class AbstractEntity extends BaseEntity {

   private static final long serialVersionUID = 8882145540383345037L;
   
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id")
   private String id;

   @Version
   @Column(name = "version")
   private int version;

   @Temporal(TemporalType.TIMESTAMP)
   private Date expired;
   
   private boolean disabled;
   
   
   @Override
   public String getId() {
       return id;
   }

   public void setId(String id) {
       this.id = id;
   }

   public int getVersion() {
       return version;
   }

   public void setVersion(int version) {
       this.version = version;
   }

   public void save() {
       getRepository().save(this);
   }

   public void remove() {
       getRepository().remove(this);
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
    public static <T extends AbstractEntity> T getByProperty(Class<T> clazz, String propName, Object value) {
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
}
