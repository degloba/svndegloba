package infrastructure.repository.jpa;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;


import java.util.List;


// JPA
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import javax.persistence.NamedQuery;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;


// Spring
import org.springframework.beans.factory.annotation.Qualifier;


import domain.support.BaseAggregateRoot;
import domain.support.ExampleSettings;
import domain.support.JpqlQuery;
import domain.support.NamedParameters;


/**
 * 
 * @author degloba
 * 
 * @param <E> JPA Entity Type (DDD: Aggregate, Entity)
 * @param <K>  Tipus de la clau (Long,String,aggregateId, ..) de l'entitat del domini
 *            
 */
public class BaseAggregateRootJpaRepository<K, E extends BaseAggregateRoot> implements IBaseAggregateRootJpaRepository<K,E>{

    @PersistenceContext(unitName="transactions-optional")
    @Qualifier(value="entityManagerFactory")
    protected EntityManager entityManager;

    private Class<E> clazz;

    @SuppressWarnings("unchecked")
    public BaseAggregateRootJpaRepository() {
        this.clazz = ((Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    /* (non-Javadoc)
     * @see infrastructure.repository.jpa.IBaseAggregateRootJpaRepository#load(java.lang.Object)
     * Retorn una entitat BaseAggregateRoot a partir del seu id
     */
    @Override
	public E load(K id) {
    	return this.entityManager.find(clazz, id);
	}

    
	/* (non-Javadoc)
	 * @see infrastructure.repository.jpa.IBaseAggregateRootJpaRepository#delete(java.lang.Object)
	 * Esborra una entitat BaseAggregateRoot a partir del seu id
	 */
	@Override
	public void delete(K id) {
        this.entityManager.remove(load(id));
	}

	/* (non-Javadoc)
	 * @see infrastructure.repository.jpa.IBaseAggregateRootJpaRepository#persist(java.lang.Object)
	 * Crea una entitat BaseAggregateRoot
	 */
	@Override
	public void persist(E entitat) {
		this.entityManager.persist(entitat);		
	}

	/* (non-Javadoc)
	 * @see infrastructure.repository.jpa.IBaseAggregateRootJpaRepository#save(java.lang.Object)
	 * Modifica una entitat BaseAggregateRoot
	 */
	@Override
	public E save(E entitat) {
		return this.entityManager.merge(entitat);
	}
	
  
	public EntityManager getEntityManager() {
		return this.entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public boolean exists(Class<?> clazz, Serializable id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <E> E get(Class<E> clazz, Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <E> E load(Class<E> clazz, Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <E> E getUnmodified(Class<E> clazz, E entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E getByBusinessKeys(Class<E> clazz, NamedParameters keyValues) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<E> findAll(Class<E> clazz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <E> CriteriaQuery createCriteriaQuery(Class<E> clazz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <E> List<E> find(CriteriaQuery criteriaQuery) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <E> E getSingleResult(CriteriaQuery criteriaQuery) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JpqlQuery createJpqlQuery(String jpql) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <E> List<E> find(JpqlQuery jpqlQuery) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <E> E getSingleResult(JpqlQuery jpqlQuery) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int executeUpdate(JpqlQuery jpqlQuery) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <E> List<E> find(NamedQuery namedQuery) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <E> E getSingleResult(NamedQuery namedQuery) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int executeUpdate(NamedQuery namedQuery) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Query createSqlQuery(String sql) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <E> List<E> find(Query sqlQuery) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <E> E getSingleResult(Query sqlQuery) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int executeUpdate(Query sqlQuery) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <E extends BaseAggregateRoot, E2 extends E> List<E> findByExample(E2 example,
			ExampleSettings<E> settings) {
		// TODO Auto-generated method stub
		return null;
	}

/*	@Override
	public <E> List<E> findByProperty(Class<E> clazz, String propertyName,
			Object propertyValue) {
		// TODO Auto-generated method stub
		return null;
	}*/

/*	@Override
	public <E> List<E> findByProperties(Class<E> clazz,
			NamedParameters properties) {
		// TODO Auto-generated method stub
		return this.entityManager.f;
	}
*/
	@Override
	public String getQueryStringOfNamedQuery(String queryName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void flush() {
		this.entityManager.flush();		
	}

	@Override
	public void refresh(BaseAggregateRoot entity) {
		this.entityManager.refresh(entity);
	}

	@Override
	public void clear() {
		this.entityManager.clear();
	}

	@Override
	public Query createNamedQuery(String queryName) {
		return this.entityManager.createNamedQuery(queryName);
	}

	@Override
	public <E> List<E> findByProperty(Class<E> clazz, String propertyName,
			Object propertyValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <E> List<E> findByProperties(Class<E> clazz,
			NamedParameters properties) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
