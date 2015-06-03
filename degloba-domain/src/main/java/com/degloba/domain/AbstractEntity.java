package com.degloba.domain;

// JPA
import javax.persistence.*;

// appengine/datastore
import com.google.appengine.api.datastore.Key;

import java.io.Serializable;
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
   private Key id;

   @Version
   @Column(name = "version")
   private int version;

   @Override
   public Key getId() {
       return id;
   }

   public void setId(Key id) {
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

   public static  <E extends com.degloba.domain.Entity> E get(Class<E> clazz, Key id) {
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

    public static <E extends com.degloba.domain.Entity> List<E> findByProperties(Class<E> clazz, Map<String, Object> propValues) {
       return getRepository().findByProperties(clazz, NamedParameters.create(propValues));
   }
}
