package infrastructure.repository.jpa;

import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 * 
 * @author degloba
 * 
 * @param <E> JPA Entity Type (DDD: Aggregate, Entity)
 * @param <K>  Tipus de la clau (Long,String,..) de l'entitat del domini
 *            
 */
public class GenericJpaRepository<E, K> {

    @PersistenceContext
    protected EntityManager entityManager;

    private Class<E> clazz;

    @SuppressWarnings("unchecked")
    public GenericJpaRepository() {
        this.clazz = ((Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    public E load(K id) {
        return entityManager.find(clazz, id);
    }

    public void delete(K id) {
        entityManager.remove(load(id));
    }

    public void persist(E entity) {
        entityManager.persist(entity);
    }

    public E save(E entity) {
        return entityManager.merge(entity);
    }

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
