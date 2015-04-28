package com.degloba.domain.seedwork;

import java.io.Serializable;
import java.util.List;

import com.degloba.domain.Entity;
import com.degloba.domain.CriteriaQuery;
import com.degloba.domain.ExampleSettings;
import com.degloba.domain.JpqlQuery;
import com.degloba.domain.NamedParameters;
import com.degloba.domain.NamedQuery;
import com.degloba.domain.SqlQuery;


/**
 * @author degloba
 * 
 * @category Defineix patro Repository.
 * Abstraccio de l'infraestructura de persistencia
 * No lligat a les entitats de Domini
 * 
 * @param <K> = Tipus d'identificador
 * @param <T> = Tipus d'entitat que gestiona
 * 
 * Storage access interface . Access and query the database for various types of entities .
 */
public interface IRepository {  
	
		<T extends Entity> void add(T entity);
		
		<T extends Entity> T save(T entity);          
		<T extends Entity> void remove(T entity);     
		     
		<T extends Entity> List<T> findAll();     
		Long getTotalResult();
		<T extends Entity> T update(T entity);
	
	    /**
	     * Entity to determine whether there is an instance of the specified ID warehousing .
	     *
	     * @param <T> Entity Type
	     * @param clazz Class entities
	     * @param id Entity标识
	     * @return 如果Entity实例存在，返回true，否则返回false
	     */
		<T extends Entity> boolean exists(Class<T> clazz, Long id);

	    /**
	     * 从Warehousing中Get指定类型、指定ID的Entity
	     *
	     * @param <T> Entity Type
	     * @param clazz Class entities
	     * @param id Entity标识
	     * @return 一个Entity实例。
	     */
	     <T extends Entity> T get(Class<T> clazz, Serializable id);

	    /**
	     * 从Warehousing中装载指定类型、指定ID的Entity
	     *
	     * @param <T> Entity Type
	     * @param clazz Class entities
	     * @param id Entity标识
	     * @return 一个Entity实例。
	     */
	     <T extends Entity> T load(final Class<T> clazz,Serializable id);
	     

	    /**
	     * 从Warehousing中Getentity参数所代表的未修改的Entity
	     *
	     * @param <T> Entity Type
	     * @param clazz Class entities
	     * @param entity 要查询的Entity
	     * @return 参数entity在Warehousing中的未修改版本
	     */
	     <T extends Entity> T getUnmodified(Class<T> clazz, T entity);
	    
	    /**
	     * 根据业务主键从Warehousing中Get指定类型的Entity
	     *
	     * @param <T> Entity Type
	     * @param clazz Class entities
	     * @param keyValues 代表业务主键值的命名参数。key为主键Property name，value为主键属性值
	     * @return 一个Entity实例。
	     */
	     <T extends Entity> T getByBusinessKeys(Class<T> clazz, NamedParameters keyValues);

	    /**
	     * 查找指定类型的所有Entity
	     *
	     * @param <T> Entity Type
	     * @param clazz Class entities
	     * @return 符合条件的Entity集合
	     */
	     <T extends Entity> List<T> findAll(Class<T> clazz);
	     

	    /**
	     * 创建条件查询
	     *
	     * @param entityClass 要查询的Entity类
	     * @param <T> Class entities型
	     * @return 一个条件查询
	     */
	     <T extends Entity> CriteriaQuery createCriteriaQuery(Class<T> clazz);

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
	    <T extends Entity> List<T> find(SqlQuery sqlQuery);

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
	     * @param <T> 查询样例的类型
	     * @param example 查询样例
	     * @param settings 查询设置
	     * @return 与example相似的T类型的范例
	     */
	    <T extends Entity, E2 extends T> List<T> findByExample(E2 example, ExampleSettings<T> settings);

	    /**
	     * 根据单一属性的值查找Entity
	     *
	     * @param <T> 要查询的Class entities型
	     * @param clazz 要查询的Class entities
	     * @param propertyName 要查询的属性
	     * @param propertyValue 匹配的属性值
	     * @return 类型为clazz的、属性propertyName的值等于propertyValue的Entity的集合
	     */
	    <T extends Entity> List<T> findByProperty(Class<T> clazz, String propertyName, Object propertyValue);

	    /**
	     * 根据多个属性的值查找Entity
	     *
	     * @param <T> 要查询的Class entities型
	     * @param clazz 要查询的Class entities
	     * @param properties 命名参数，其中key为Property name，value为要匹配的属性值。
	     * @return 类型为clazz、多个属性分别等于指定的属性值的Entity的集合。
	     */
	    <T extends Entity> List<T> findByProperties(Class<T> clazz, NamedParameters properties);
	    
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
	     * 使用数据库中的最新数据更新Entity的当前状态。Entity中的任何已改变但未持久化的属性值将被数据库中的最新值覆盖。
	     *
	     * @param entity 要刷新的Entity
	     */
	    <E extends Entity> void refresh(E entity);

	    /**
	     * 清空持久化缓存
	     */
	    void clear();

				

	
}