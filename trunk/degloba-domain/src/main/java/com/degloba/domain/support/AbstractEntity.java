package com.degloba.domain.support;

import javax.persistence.*;

import com.degloba.domain.NamedParameters;
import com.degloba.utils.Utils;

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
    private Long id;

    @Version
    @Column(name = "version")
    private int version;

    /**
     * 获得实体的标识
     *
     * @return 实体的标识
     */
    @Override
    public Long getId() {
        return id;
    }

    /**
     * 设置实体的标识
     *
     * @param id 要设置的实体标识
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获得实体的版本号。持久化框架以此实现乐观锁。
     *
     * @return 实体的版本号
     */
    public int getVersion() {
        return version;
    }

    /**
     * 设置实体的版本号。持久化框架以此实现乐观锁。
     *
     * @param version 要设置的版本号
     */
    public void setVersion(int version) {
        this.version = version;
    }

    /**
     * 将实体本身持久化到数据库
     */
    public void save() {
        //getRepository().save(this);
    }

    /**
     * 将实体本身从数据库中删除
     */
    public void remove() {
        //getRepository().remove(this);
    }

    /**
     * 根据实体类型和ID从仓储中获取实体
     * @param <T> 实体类型
     * @param clazz 实体的类
     * @param id 实体的ID
     * @return 类型为T或T的子类型，ID为id的实体。
     */
    public static  <E extends com.degloba.domain.seedwork.Entity> E get(Class<E> clazz, Serializable id) {
        return (E) getRepository().get(clazz, id);
    }

    /**
     * 查找实体在数据库中的未修改版本
     * @param <T> 实体类型
     * @param clazz 实体的类
     * @param entity  实体
     * @return 实体的未修改版本。
     */
    public static <T extends BaseEntity> T getUnmodified(Class<T> clazz, T entity) {
        return (T) getRepository().getUnmodified(clazz, entity);
    }

    /**
     * 根据实体类型和ID从仓储中加载实体(与get()方法的区别在于除id外所有的属性值都未填充)
     * @param <T> 实体类型
     * @param clazz 实体的类
     * @param id 实体的ID
     * @return 类型为T或T的子类型，ID为id的实体。
     */
    public static <T extends BaseEntity> T load(Class<T> clazz, Serializable id) {
        return (T) getRepository().load(clazz, id);
    }

    /**
     * 查找指定类型的所有实体
     * @param <T> 实体所属的类型
     * @param clazz 实体所属的类
     * @return 符合条件的实体列表
     */
    public static <E extends BaseEntity> List<E> findAll(Class<E> clazz) {
        return Utils.castList(clazz,  getRepository().createCriteriaQuery(clazz).list());
    }

    /**
     * 根据单个属性值以“属性=属性值”的方式查找实体
     * @param <T> 实体所属的类型
     * @param clazz 实体所属的类
     * @param propName 属性名
     * @param value 匹配的属性值
     * @return 符合条件的实体列表
     */
    public static <T extends BaseEntity> List<T> findByProperty(Class<T> clazz, String propName, Object value) {
        return getRepository().findByProperty(clazz, propName, value);
    }

    /**
     * 根据多个属性值以“属性=属性值”的方式查找实体，例如查找name="张三", age=35的员工。
     * @param <T> 实体所属的类型
     * @param clazz 实体所属的类
     * @param propValues 属性值匹配条件
     * @return 符合条件的实体列表
     */
    public static <E extends com.degloba.domain.seedwork.Entity> List<E> findByProperties(Class<E> clazz, Map<String, Object> propValues) {
        return getRepository().findByProperties(clazz, NamedParameters.create(propValues));
    }
}
