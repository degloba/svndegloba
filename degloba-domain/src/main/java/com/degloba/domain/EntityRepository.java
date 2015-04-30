package com.degloba.domain;

import java.io.Serializable;
import java.util.List;

import com.google.appengine.api.datastore.Key;


/**
 * Warehousing access interface. Access and query the database for various types of Entity.
 */
public interface EntityRepository {

    /**
     * The Entity (either new or modified) is saved to the Warehousing in.
     *
     * @param <T> Class entities Type
     * @param entity To store the Entity instance.
     * @return After persistent current Entity
     */
     <T extends com.degloba.domain.Entity> T save(T entity);
    

    /**
     * Warehousing will be removed from the Entity. If Warehousing does not exist in this instance EntityNotExistedException will throw an exception.
     *
     * @param entity To delete the Entity instance.
     */
    void remove(com.degloba.domain.Entity entity);

    /**
     * Determine whether there is a specified ID Warehousing in Entity instance.
     *
     * @param <T> Entity Type
     * @param clazz Class entities
     * @param id Entity identification
     * @return If the Entity instance exists, returns true, otherwise false
     */
    <T extends com.degloba.domain.Entity> boolean exists(Class<T> clazz, Key id);

    /**
     * Get the Entity Warehousing in the specified type, the specified ID
     *
     * @param <T> Entity Type
     * @param clazz Class entities
     * @param id Entity identification
     * @return An Entity instance.
     */
    ////////<T extends Entity> T get(Class<T> clazz, Serializable id);
    <T extends com.degloba.domain.Entity> T get(Class<T> clazz, Key id);

    /**
     * Load the specified type from Warehousing, specify the ID of the Entity
     *
     * @param <T> Entity Type
     * @param clazz Class entities
     * @param id Entity identification
     * @return An Entity instance.
     */
    <T extends com.degloba.domain.Entity> T load(Class<T> clazz, Serializable id);

    /**
     * From Warehousing in Getentity parameters represent unmodified Entity
     *
     * @param <T> Entity Type
     * @param clazz Class entities
     * @param entity To query the Entity
     * @return Parameter entity in the unmodified version Warehousing
     */
    <T extends com.degloba.domain.Entity> T getUnmodified(Class<T> clazz, T entity);
    
    /**
     * Entity specified type from the Get Warehousing according to Natural key
     *
     * @param <T> Entity Type
     * @param clazz Class entities
     * @param keyValues Representatives named primary key business parameters. key primary key Property name, value as a primary key attribute values
     * @return An Entity instance.
     */
    <T extends com.degloba.domain.Entity> T getByBusinessKeys(Class<T> clazz, NamedParameters keyValues);

    /**
     * Find all Entity specified type
     *
     * @param <T> Entity Type
     * @param clazz Class entities
     * @return Eligible Entity collection
     */
    <T extends com.degloba.domain.Entity> List<T> findAll(Class<T> clazz);

    /**
     * Creating conditions for inquiry
     *
     * @param entityClass Creative Entity class to query
     * @param <T> Class type entities
     * @return A conditional query
     */
    <T extends com.degloba.domain.Entity> CriteriaQuery createCriteriaQuery(Class<T> entityClass);

    /**
     * 执行条件查询，返回Eligible Entity List
     *
     * @param criteriaQuery 要执行的条件查询
     * @param <T> 返回结果元素类型
     * @return 符合查询条件的Entity列表
     */
    <T> List<T> find(CriteriaQuery criteriaQuery);

    /**
     * 执行条件查询，返回符合条件的单个Entity
     *
     * @param criteriaQuery 要执行的条件查询
     * @param <T> 返回结果类型
     * @return 符合查询条件的单个结果
     */
    <T> T getSingleResult(CriteriaQuery criteriaQuery);

    /**
     * 创建JPQL查询
     *
     * @param jpql JPQL语句
     * @return 一个JPQL查询
     */
    JpqlQuery createJpqlQuery(String jpql);

    /**
     * 执行JPQL查询，返回Eligible Entity List
     *
     * @param jpqlQuery 要执行的JPQL查询
     * @param <T> 返回结果元素类型
     * @return 符合查询条件的结果列表
     */
    <T> List<T> find(JpqlQuery jpqlQuery);

    /**
     * 执行JPQL查询，返回符合条件的单个Entity
     *
     * @param jpqlQuery 要执行的JPQL查询
     * @param <T> 返回结果类型
     * @return 符合查询条件的单个结果
     */
    <T> T getSingleResult(JpqlQuery jpqlQuery);

    /**
     * Perform the update warehousing operation.
     *
     * @param jpqlQuery 要执行的JPQL查询。
     * @return Number of updated or deleted entities
     */
    int executeUpdate(JpqlQuery jpqlQuery);

    /**
     * 创建命名查询
     *
     * @param queryName 命名查询的名字
     * @return 一个命名查询
     */
    NamedQuery createNamedQuery(String queryName);

    /**
     * 执行命名查询，返回Eligible Entity List
     *
     * @param namedQuery 要执行的命名查询
     * @param <T> 返回结果元素类型
     * @return 符合查询条件的结果列表
     */
    <T> List<T> find(NamedQuery namedQuery);

    /**
     * 执行命名查询，返回符合条件的单个Entity
     *
     * @param namedQuery 要执行的命名查询
     * @param <T> 返回结果类型
     * @return 符合查询条件的单个结果
     */
    <T> T getSingleResult(NamedQuery namedQuery);

    /**
     * 使用命名查询Perform the update warehousing operation.
     *
     * @param namedQuery 要执行的命名查询。
     * @return Number of updated or deleted entities
     */
    int executeUpdate(NamedQuery namedQuery);

    /**
     * 创建原生SQL查询
     *
     * @param sql SQL语句
     * @return 一个原生SQL查询
     */
    SqlQuery createSqlQuery(String sql);

    /**
     * 执行SQL查询，返回Eligible Entity List
     *
     * @param sqlQuery 要执行的SQL查询。
     * @param <T> 返回结果元素类型
     * @return 符合查询条件的结果列表
     */
    <T> List<T> find(SqlQuery sqlQuery);

    /**
     * 执行SQL查询，返回符合条件的单个Entity
     *
     * @param sqlQuery 要执行的SQL查询。
     * @param <T> 返回结果类型
     * @return 符合查询条件的单个结果
     */
    <T> T getSingleResult(SqlQuery sqlQuery);

    /**
     * 使用SQL查询Perform the update warehousing operation.
     *
     * @param sqlQuery 要执行的SQL查询。
     * @return Number of updated or deleted entities
     */
    int executeUpdate(SqlQuery sqlQuery);

    /**
     * 按例查询。
     *
     * @param <T> 查询的目标Entity Type
     * @param <E> 查询样例的类型
     * @param example 查询样例
     * @param settings 查询设置
     * @return 与example相似的T类型的范例
     */
    <T extends com.degloba.domain.Entity, E extends T> List<T> findByExample(E example, ExampleSettings<T> settings);

    /**
     * 根据单一属性的值查找Entity
     *
     * @param <T> 要查询的Class entities型
     * @param clazz 要查询的Class entities
     * @param propertyName 要查询的属性
     * @param propertyValue 匹配的 Property Value
     * @return 类型为clazz的、属性propertyName的值等于propertyValue的Entity的集合
     */
    <T extends com.degloba.domain.Entity> List<T> findByProperty(Class<T> clazz, String propertyName, Object propertyValue);

    /**
     * 根据多个属性的值查找Entity
     *
     * @param <T> 要查询的Class entities型
     * @param clazz 要查询的Class entities
     * @param properties 命名参数，其中key为Property name，value为要匹配的 Property Value。
     * @return 类型为clazz、多个属性分别等于指定的 Property Value的Entity的集合。
     */
    <T extends com.degloba.domain.Entity> List<T> findByProperties(Class<T> clazz, NamedParameters properties);
    
    /**
     * Get命名查询的查询字符串
     * @param queryName 命名查询的名字
     * @return 命名查询对应的JPQL字符串
     */
    String getQueryStringOfNamedQuery(String queryName);


    /**
     * 将内存中的持久化对象状态即时写入数据库
     */
    void flush();

    /**
     * 使用数据库中的最新数据更新Entity的当前状态。Entity中的任何已改变但未持久化的 Property Value将被数据库中的最新值覆盖。
     *
     * @param entity 要刷新的Entity
     */
    void refresh(com.degloba.domain.Entity entity);

    /**
     * 清空持久化缓存
     */
    void clear();

}
