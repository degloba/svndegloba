package com.degloba.domain;
import javax.persistence.*;

import com.degloba.utils.Utils;
import com.google.appengine.api.datastore.Key;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
* An abstract entity class , provide ID and release properties , as well as the basic method of persistence
*
* @author yang
*
*/
@MappedSuperclass
public abstract class AbstractEntity extends BaseEntity {

   private static final long serialVersionUID = 8882145540383345037L;

   //@Id  //ELIMINAT PER PERE
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id")
   private Key id;

   @Version
   @Column(name = "version")
   private int version;

   /**
    * 获得Entity的标识
    *
    * @return Entity的标识
    */
   @Override
   public Key getId() {
       return id;
   }

   /**
    * 设置Entity的标识
    *
    * @param id 要设置的Entity标识
    */
   public void setId(Key id) {
       this.id = id;
   }

   /**
    * 获得Entity的版本号。持久化框架以此实现乐观锁。
    *
    * @return Entity的版本号
    */
   public int getVersion() {
       return version;
   }

   /**
    * 设置Entity的版本号。持久化框架以此实现乐观锁。
    *
    * @param version 要设置的版本号
    */
   public void setVersion(int version) {
       this.version = version;
   }

   /**
    * 将Entity本身持久化到数据库
    */
   public void save() {
       getRepository().save(this);
   }

   /**
    * 将Entity本身从数据库中删除
    */
   public void remove() {
       getRepository().remove(this);
   }

   /**
    * 根据Entity Type和ID从Warehousing中GetEntity
    * @param <T> Entity Type
    * @param clazz Class entities
    * @param id ID entity
    * @return 类型为T或T的子类型，ID为id的Entity。
    */
   public static  <E extends com.degloba.domain.Entity> E get(Class<E> clazz, Key id) {
       return (E) getRepository().get(clazz, id);
   }

   /**
    * 查找Entity在数据库中的未修改版本
    * @param <T> Entity Type
    * @param clazz Class entities
    * @param entity  Entity
    * @return Entity的未修改版本。
    */
   public static <T extends BaseEntity> T getUnmodified(Class<T> clazz, T entity) {
       return (T) getRepository().getUnmodified(clazz, entity);
   }

   /**
    * 根据Entity Type和ID从Warehousing中加载Entity(与get()方法的区别在于除id外所有的属性值都未填充)
    * @param <T> Entity Type
    * @param clazz Class entities
    * @param id ID entity
    * @return 类型为T或T的子类型，ID为id的Entity。
    */
   public static <T extends BaseEntity> T load(Class<T> clazz, Serializable id) {
       return (T) getRepository().load(clazz, id);
   }

   /**
    * 查找指定类型的所有Entity
    * @param <T> Entity Class belongs型
    * @param clazz Entity Class belongs
    * @return Eligible Entity List
    */
   public static <E extends BaseEntity> List<E> findAll(Class<E> clazz) {
       return getRepository().createCriteriaQuery(clazz).list();
   }

   /**
    * 根据单个属性值以“属性=属性值”的方式查找Entity
    * @param <T> Entity Class belongs型
    * @param clazz Entity Class belongs
    * @param propName Property name
    * @param value 匹配的属性值
    * @return Eligible Entity List
    */
   public static <T extends BaseEntity> List<T> findByProperty(Class<T> clazz, String propName, Object value) {
       return getRepository().findByProperty(clazz, propName, value);
   }

   /**
    * 根据多个属性值以“属性=属性值”的方式查找Entity，例如查找name="张三", age=35的员工。
    * @param <T> Entity Class belongs型
    * @param clazz Entity Class belongs
    * @param propValues 属性值匹配条件
    * @return Eligible Entity List
    */
    public static <E extends com.degloba.domain.Entity> List<E> findByProperties(Class<E> clazz, Map<String, Object> propValues) {
       return getRepository().findByProperties(clazz, NamedParameters.create(propValues));
   }
}
