package infrastructure.repository.jpa;

import java.lang.reflect.ParameterizedType;

// JPA
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

// Spring
import org.springframework.beans.factory.annotation.Qualifier;

import domain.support.BaseAggregateRoot;
import infrastructure.seedwork.Repository;


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

	
}
