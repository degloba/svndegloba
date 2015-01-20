package infrastructure.repository.jpa;

import java.io.Serializable;
import java.util.List;


// JPA
import javax.persistence.EntityManager;

//
import domain.support.CriteriaQuery;
import domain.support.ExampleSettings;
import domain.support.JpqlQuery;
import domain.support.NamedParameters;
import domain.support.NamedQuery;
import domain.support.SqlQuery;


public interface IBaseAggregateRootJpaRepository<E> {
	 
	 /**
	     * å°†å®žä½“ï¼ˆæ— è®ºæ˜¯æ–°çš„è¿˜æ˜¯ä¿®æ”¹äº†çš„ï¼‰ä¿�å­˜åˆ°ä»“å‚¨ä¸­ã€‚
	     *
	     * @param <T> å®žä½“çš„ç±»åž‹
	     * @param entity è¦�å­˜å‚¨çš„å®žä½“å®žä¾‹ã€‚
	     * @return æŒ�ä¹…åŒ–å�Žçš„å½“å‰�å®žä½“
	     */
//	    <T extends E> T save(T entity);
	    
	 public EntityManager getEntityManager();

	 public void setEntityManager(EntityManager entityManager); 
	 
	 
	 
	<K, E> E load(K id);

	void persist(E entitat); 
	 //<E> void  persist(E entitat);

	 //<E> E save(E entitat);
	 E save(E entitat);
	 
	   /**
	     * å°†å®žä½“ä»Žä»“å‚¨ä¸­åˆ é™¤ã€‚å¦‚æžœä»“å‚¨ä¸­ä¸�å­˜åœ¨æ­¤å®žä¾‹å°†æŠ›å‡ºEntityNotExistedExceptionå¼‚å¸¸ã€‚
	     *
	     * @param entity è¦�åˆ é™¤çš„å®žä½“å®žä¾‹ã€‚
	     */
	 void remove(E entity);

     
	   	    	 
	 /**********************************************************************************************************/
	 
	   /**
	     * Entity to determine whether there is an instance of the specified ID warehousing .
	     *
	     * @param <T> 实体类型
	     * @param clazz 实体的类
	     * @param id 实体标识
	     * @return 如果实体实例存在，返回true，否则返回false
	     */
	 	 boolean exists(Class<E> clazz, Serializable id);
	     //<T extends E> boolean exists(Class<T> clazz, Serializable id);

	    /**
	     * 从仓储中获取指定类型、指定ID的实体
	     *
	     * @param <T> 实体类型
	     * @param clazz 实体的类
	     * @param id 实体标识
	     * @return 一个实体实例。
	     */
	    E get(Class<E> clazz, Serializable id);
	     //<T extends Entity> T get(Class<T> clazz, Serializable id);

	    /**
	     * 从仓储中装载指定类型、指定ID的实体
	     *
	     * @param <T> 实体类型
	     * @param clazz 实体的类
	     * @param id 实体标识
	     * @return 一个实体实例。
	     */
	      E load(Class<E> clazz, Serializable id);
	    //<T extends Entity> T load(Class<T> clazz, Serializable id);

	    /**
	     * 从仓储中获取entity参数所代表的未修改的实体
	     *
	     * @param <T> 实体类型
	     * @param clazz 实体的类
	     * @param entity 要查询的实体
	     * @return 参数entity在仓储中的未修改版本
	     */
	    E getUnmodified(Class<E> clazz, E entity);
	    //<T extends Entity> T getUnmodified(Class<T> clazz, T entity);
	    
	    /**
	     * 根据业务主键从仓储中获取指定类型的实体
	     *
	     * @param <T> 实体类型
	     * @param clazz 实体的类
	     * @param keyValues 代表业务主键值的命名参数。key为主键属性名，value为主键属性值
	     * @return 一个实体实例。
	     */
	    E getByBusinessKeys(Class<E> clazz, NamedParameters keyValues);
	     //<T extends Entity> T getByBusinessKeys(Class<T> clazz, NamedParameters keyValues);

	    /**
	     * 查找指定类型的所有实体
	     *
	     * @param <T> 实体类型
	     * @param clazz 实体的类
	     * @return 符合条件的实体集合
	     */
	    List<E> findAll(Class<E> clazz);
	    //<T extends Entity> List<T> findAll(Class<T> clazz);

	    /**
	     * 创建条件查询
	     *
	     * @param entityClass 要查询的实体类
	     * @param <T> 实体的类型
	     * @return 一个条件查询
	     */
	    CriteriaQuery createCriteriaQuery(Class<E> clazz);
	    //<T extends Entity> CriteriaQuery createCriteriaQuery(Class<T> entityClass);

	    /**
	     * 执行条件查询，返回符合条件的实体列表
	     *
	     * @param criteriaQuery 要执行的条件查询
	     * @param <T> 返回结果元素类型
	     * @return 符合查询条件的实体列表
	     */
	     List<E> find(CriteriaQuery criteriaQuery);
	    //<T> List<T> find(CriteriaQuery criteriaQuery);

	    /**
	     * 执行条件查询，返回符合条件的单个实体
	     *
	     * @param criteriaQuery 要执行的条件查询
	     * @param <T> 返回结果类型
	     * @return 符合查询条件的单个结果
	     */
	     E getSingleResult(CriteriaQuery criteriaQuery);
	     //<T> T getSingleResult(CriteriaQuery criteriaQuery);

	    /**
	     * 创建JPQL查询
	     *
	     * @param jpql JPQL语句
	     * @return 一个JPQL查询
	     */
	    JpqlQuery createJpqlQuery(String jpql);

	    /**
	     * 执行JPQL查询，返回符合条件的实体列表
	     *
	     * @param jpqlQuery 要执行的JPQL查询
	     * @param <T> 返回结果元素类型
	     * @return 符合查询条件的结果列表
	     */
	     List<E> find(JpqlQuery jpqlQuery);
	    //<T> List<T> find(JpqlQuery jpqlQuery);

	    /**
	     * 执行JPQL查询，返回符合条件的单个实体
	     *
	     * @param jpqlQuery 要执行的JPQL查询
	     * @param <T> 返回结果类型
	     * @return 符合查询条件的单个结果
	     */
	     E getSingleResult(JpqlQuery jpqlQuery);
	    //<T> T getSingleResult(JpqlQuery jpqlQuery);

	    /**
	     * 执行更新仓储的操作。
	     *
	     * @param jpqlQuery 要执行的JPQL查询。
	     * @return 被更新或删除的实体的数量
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
	     * 执行命名查询，返回符合条件的实体列表
	     *
	     * @param namedQuery 要执行的命名查询
	     * @param <T> 返回结果元素类型
	     * @return 符合查询条件的结果列表
	     */
	     List<E> find(NamedQuery namedQuery);
	    //<T> List<T> find(NamedQuery namedQuery);

	    /**
	     * 执行命名查询，返回符合条件的单个实体
	     *
	     * @param namedQuery 要执行的命名查询
	     * @param <T> 返回结果类型
	     * @return 符合查询条件的单个结果
	     */
	     E getSingleResult(NamedQuery namedQuery);
	    //<T> T getSingleResult(NamedQuery namedQuery);

	    /**
	     * 使用命名查询执行更新仓储的操作。
	     *
	     * @param namedQuery 要执行的命名查询。
	     * @return 被更新或删除的实体的数量
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
	     * 执行SQL查询，返回符合条件的实体列表
	     *
	     * @param sqlQuery 要执行的SQL查询。
	     * @param <T> 返回结果元素类型
	     * @return 符合查询条件的结果列表
	     */
	     List<E> find(SqlQuery sqlQuery);
	    //<T> List<T> find(SqlQuery sqlQuery);

	    /**
	     * 执行SQL查询，返回符合条件的单个实体
	     *
	     * @param sqlQuery 要执行的SQL查询。
	     * @param <T> 返回结果类型
	     * @return 符合查询条件的单个结果
	     */
	     E getSingleResult(SqlQuery sqlQuery);
	    //<T> List<T> find(SqlQuery sqlQuery);

	    /**
	     * 使用SQL查询执行更新仓储的操作。
	     *
	     * @param sqlQuery 要执行的SQL查询。
	     * @return 被更新或删除的实体的数量
	     */
	    int executeUpdate(SqlQuery sqlQuery);

	    /**
	     * 按例查询。
	     *
	     * @param <T> 查询的目标实体类型
	     * @param <E> 查询样例的类型
	     * @param example 查询样例
	     * @param settings 查询设置
	     * @return 与example相似的T类型的范例
	     */
	    <E, E2 extends E> List<E> findByExample(E2 example, ExampleSettings<E> settings);
	    //<T extends Entity, E extends T> List<T> findByExample(E example, ExampleSettings<T> settings);

	    /**
	     * 根据单一属性的值查找实体
	     *
	     * @param <T> 要查询的实体的类型
	     * @param clazz 要查询的实体的类
	     * @param propertyName 要查询的属性
	     * @param propertyValue 匹配的属性值
	     * @return 类型为clazz的、属性propertyName的值等于propertyValue的实体的集合
	     */
	     List<E> findByProperty(Class<E> clazz, String propertyName, Object propertyValue);
	    //<T extends Entity> List<T> findByProperty(Class<T> clazz, String propertyName, Object propertyValue);

	    /**
	     * 根据多个属性的值查找实体
	     *
	     * @param <T> 要查询的实体的类型
	     * @param clazz 要查询的实体的类
	     * @param properties 命名参数，其中key为属性名，value为要匹配的属性值。
	     * @return 类型为clazz、多个属性分别等于指定的属性值的实体的集合。
	     */
	     List<E> findByProperties(Class<E> clazz, NamedParameters properties);
	    //<T extends Entity> List<T> findByProperties(Class<T> clazz, NamedParameters properties);
	    
	    /**
	     * 获取命名查询的查询字符串
	     * @param queryName 命名查询的名字
	     * @return 命名查询对应的JPQL字符串
	     */
	    String getQueryStringOfNamedQuery(String queryName);


	    /**
	     * 将内存中的持久化对象状态即时写入数据库
	     */
	    void flush();

	    /**
	     * 使用数据库中的最新数据更新实体的当前状态。实体中的任何已改变但未持久化的属性值将被数据库中的最新值覆盖。
	     *
	     * @param entity 要刷新的实体
	     */
	    void refresh(E entity);

	    /**
	     * 清空持久化缓存
	     */
	    void clear();




}
